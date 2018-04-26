package com.i000.stock.user.service.impl;

import com.i000.stock.user.api.service.PriceService;
import com.i000.stock.user.dao.bo.BaseSearchVo;
import com.i000.stock.user.dao.mapper.CompanyMapper;
import com.i000.stock.user.dao.mapper.PriceMapper;
import com.i000.stock.user.dao.model.Company;
import com.i000.stock.user.dao.model.Price;
import com.i000.stock.user.service.impl.external.ExternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 16:43 2018/4/25
 * @Modified By:
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceMapper priceMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Autowired
    private ExternalServiceImpl externalService;

    @Override
    public Price get(String code) {
        return externalService.getPrice(code);
    }

    @Override
    public Price get(String code, String time) {
        return priceMapper.find(code, time);
    }

    @Override
    public void save() {
        List<Price> prices = findNotLazy();
        if (!CollectionUtils.isEmpty(prices)) {
            if (priceMapper.findCount(prices.get(0).getDate()) > 0) {
                return;
            }
            for (Price price : prices) {
                priceMapper.insert(price);
            }
        }
    }

    @Override
    public List<Price> findNotLazy() {
        List<Price> prices = findPrice(BaseSearchVo.builder().pageSize(100).pageNo(1).build());
        int pageSize = (int) (companyMapper.count() / 100 + 1);
        for (int i = 2; i <= pageSize; i++) {
            prices.addAll(findPrice(BaseSearchVo.builder().pageNo(i).pageSize(100).build()));
        }
        return prices;
    }


    private List<Price> findPrice(BaseSearchVo baseSearchVo) {
        baseSearchVo.setStart();
        List<Company> search = companyMapper.search(baseSearchVo);
        List<String> index = search.stream().map(a -> a.getCode()).collect(Collectors.toList());
        return externalService.getPrice(index);
    }
}
