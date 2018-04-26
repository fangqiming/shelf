package com.i000.stock.user.web.controller;

import com.i000.stock.user.api.service.IndexService;
import com.i000.stock.user.core.result.Results;
import com.i000.stock.user.core.result.base.ResultEntity;
import com.i000.stock.user.dao.model.IndexInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:43 2018/4/25
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     * 127.0.0.1:8082/index/get_now
     * 获取最新的指数信息
     *
     * @return
     */
    @GetMapping("/get_now")
    public ResultEntity get() {
        List<IndexInfo> indices = indexService.get();
        return Results.newListResultEntity(indices);
    }

    /**
     * 获取某一天的指数信息
     * 127.0.0.1:8082/index/get_old?date='2018-04-25'
     * @param date
     * @return
     */
    @GetMapping("/get_old")
    public ResultEntity getOld(String date) {
        List<IndexInfo> indices = indexService.get(date);
        return Results.newListResultEntity(indices);
    }

    /**
     * 127.0.0.1:8082/index/save
     * 测试：用于保存指数信息
     * @return
     */
    @GetMapping("/save")
    public ResultEntity save() {
        indexService.save();
        return Results.newEmptyResultEntity();
    }
}
