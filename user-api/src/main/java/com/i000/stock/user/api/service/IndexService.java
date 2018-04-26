package com.i000.stock.user.api.service;

import com.i000.stock.user.dao.model.IndexInfo;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 16:38 2018/4/25
 * @Modified By:
 */
public interface IndexService {

    /**
     * 获取实时的指数数据
     *
     * @return
     */
    List<IndexInfo> get();

    /**
     * 获取某一天的指数数据
     *
     * @param date
     * @return
     */
    List<IndexInfo> get(String date);

    /**
     * 将获取到的指数信息保存到数据库
     */
    void save();
}
