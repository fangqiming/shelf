package com.i000.stock.user.web.controller;

import com.i000.stock.user.api.entity.vo.PriceVos;
import com.i000.stock.user.api.service.PriceService;
import com.i000.stock.user.core.result.Results;
import com.i000.stock.user.core.result.base.ResultEntity;
import com.i000.stock.user.dao.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:50 2018/4/25
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/price")
public class PriceController {

    @Resource
    private PriceService priceService;

    /**
     * 127.0.0.1:8082/price/get_now?code=600000
     *
     * @param code
     * @return
     */
    @GetMapping("/get_now")
    public ResultEntity get_now(@RequestParam String code) {
        Price price = priceService.get(code);
        return Results.newSingleResultEntity(price);
    }

    /**
     * 127.0.0.1:8082/price/get_old?code=600000&date=2018-04-25
     *
     * @param code
     * @param date
     * @return
     */
    @GetMapping("/get_old")
    public ResultEntity get_now(@RequestParam String code, @RequestParam String date) {
        Price price = priceService.get(code, date);
        return Results.newSingleResultEntity(price);
    }

    /**
     * 127.0.0.1:8082/price/find_now_complete
     *
     * @return
     */
    @GetMapping("/find_now_complete")
    public ResultEntity findNowComplete() {
        List<Price> notLazy = priceService.findNotLazy();
        return Results.newListResultEntity(notLazy);
    }

    /**
     * 127.0.0.1:8082/price/find_now_simple
     * @return
     */
    @GetMapping("/find_now_simple")
    public ResultEntity findNowSimple() {
        List<Price> notLazy = priceService.findNotLazy();
        PriceVos result = new PriceVos();
        notLazy.forEach(a -> {
            result.getName().add(a.getName());
            result.getCode().add(a.getCode());
            result.getOpen().add(a.getOpen());
            result.getClose().add(a.getClose());
            result.getVolume().add(a.getVolume());
            result.getHigh().add(a.getHigh());
            result.getLow().add(a.getLow());
            result.getBuy().add(a.getBuy());
            result.getSell().add(a.getSell());
            result.getIsOpen().add(a.getIsOpen());
            result.setDate(a.getDate());
        });
        return Results.newSingleResultEntity(result);
    }
}
