package com.i000.stock.user.api.service;

import com.i000.stock.user.dao.model.Price;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:40 2018/4/25
 * @Modified By:
 */
public interface PriceService {

    /**
     * 根据指定的股票代码获取股票的实时价格
     *
     * @param code
     * @return
     */
    Price get(String code);

    /**
     * 根据指定的股票代码和时间查询股票价格
     *
     * @param code
     * @param time
     * @return
     */
    Price get(String code, String time);

    /**
     * 用于将全部的股票价格保存到数据库
     */
    void save();

    /**
     * 获取全部的股票价格（懒加载）
     *
     * @return
     */
    List<Price> findNotLazy();
}
