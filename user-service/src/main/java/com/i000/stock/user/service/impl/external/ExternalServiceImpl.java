package com.i000.stock.user.service.impl.external;

import com.alibaba.fastjson.JSONObject;
import com.i000.stock.user.api.entity.bo.IndexBo;
import com.i000.stock.user.dao.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:41 2018/4/25
 * @Modified By:
 */
@Slf4j
@Service
public class ExternalServiceImpl {

    private ExternalService service;

    @PostConstruct
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                //此处的baseUrl没有意义，只是为了处理不加该初始化参数导致的启动报错问题
                .baseUrl("https://www.empty.net/")
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ExternalService.class);
    }

    /**
     * 批量获取价格
     *
     * @param indexs
     * @return
     */
    public List<Price> getPrice(List<String> indexs) {
        if (!CollectionUtils.isEmpty(indexs)) {
            String param = "list=";
            for (String index : indexs) {
                param += index.startsWith("60") ? "sh" + index + "," : "sz" + index + ",";
            }
            String priceStr = execute(service.getPrice(param));
            return parsePriceStr(priceStr);

        }
        return new ArrayList<>(0);
    }

    /**
     * 获取一个价格
     *
     * @param index
     * @return
     */
    public Price getPrice(String index) {
        List list = Arrays.asList(index);
        List<Price> prices = getPrice(list);
        if (!CollectionUtils.isEmpty(prices)) {
            return prices.get(0);
        }
        return new Price();

    }

    /**
     * 获取指数
     *
     * @return
     */
    public IndexBo getIndex() {
        Call<JSONObject> index = service.getIndex();
        return JSONObject.parseObject(execute(index).toString(),IndexBo.class);
    }


    private List<Price> parsePriceStr(String str) {
        List<Price> result = new ArrayList<>();
        if (!StringUtils.isBlank(str)) {
            str = str.replaceAll("\"", "");
            String[] prices = str.split("\n");
            for (String price : prices) {
                if (price.length() > 50) {
                    String code = price.split("=")[0].split("sh|sz")[1];
                    String[] info = price.split("=")[1].split(";")[0].split(",");
                    System.out.println(info[32]);
                    result.add(Price.builder().code(code)
                            .buy(new BigDecimal(info[6]))
                            .close(new BigDecimal(info[2]))
                            .high(new BigDecimal(info[4]))
                            .isOpen(Byte.valueOf(info[32]))
                            .low(new BigDecimal(info[5]))
                            .name(info[0])
                            .volume(new BigDecimal(info[8]))
                            .open(new BigDecimal(info[1]))
                            .sell(new BigDecimal(info[7]))
                            .date(info[30]).build());
                }

            }
        }
        return result;
    }


    private <T> T execute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format("请求失败, 请求地址: %s, HttpStatus: %s", call.request().url(), response.code()));
            } else {
                return response.body();
            }
        } catch (Exception e) {
            log.error(String.format("远端接口访问失败, 请求地址: %s", call.request().url()), e);
            return null;
        }
    }
}