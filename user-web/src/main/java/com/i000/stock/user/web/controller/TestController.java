package com.i000.stock.user.web.controller;

import com.i000.stock.user.api.entity.vo.ExampleVo;
import com.i000.stock.user.api.service.ExampleService;
import com.i000.stock.user.api.service.IndexService;
import com.i000.stock.user.api.service.PriceService;
import com.i000.stock.user.core.resolver.JsonParameter;
import com.i000.stock.user.core.result.Results;
import com.i000.stock.user.core.result.base.ResultEntity;
import com.i000.stock.user.core.util.ConvertUtils;
import com.i000.stock.user.core.util.ValidationUtils;
import com.i000.stock.user.dao.model.Example;
import com.i000.stock.user.service.impl.external.ExternalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 10:31 2018/4/23
 * @Modified By:
 */
@Slf4j
@RestController
public class TestController {


    @Resource
    private ExampleService exampleService;

    @Resource
    private ExternalServiceImpl externalService;


    /**
     * 测试数据库访问
     * 127.0.0.1:8082/test_dao
     *
     * @return
     */
    @GetMapping("/test_dao")
    public ResultEntity testDao() {
        Example example = exampleService.get(1L);
        ExampleVo result = ConvertUtils.beanConvert(example, new ExampleVo());
        return Results.newSingleResultEntity(result);
    }

    /**
     * 测试异常处理
     * 127.0.0.1:8082/test_exception?a=a
     *
     * @return
     */
    @GetMapping("/test_exception")
    public ResultEntity testException(Integer a) {
        System.out.println(externalService.getIndex());
        System.out.println(System.currentTimeMillis());
        return Results.newEmptyResultEntity();
    }

    /**
     * 测试类型转化与数据校验
     * 127.0.0.1:8082/test_conversion?name=h1&age=-1
     *
     * @return
     */
    @GetMapping("/test_conversion")
    public ResultEntity testConversion(ExampleVo example) {
        ValidationUtils.validate(example);

        return Results.newSingleResultEntity(example);
    }

    /**
     * 测试数据绑定
     * 127.0.0.1:8082/test_postDataBind?updatedTime=1524463520741&name=fangqiming&age=-100
     *
     * @return
     */
    @GetMapping("/test_postDataBind")
    public ResultEntity testPostDataBind(ExampleVo exampleVo) {
        System.out.println(exampleVo);
        return Results.newEmptyResultEntity();
    }

    /**
     * 测试数据获取
     * 127.0.0.1:8082/test_getData
     * 请求体数据：{"name":"fangqiming","age":-100,"updatedTime":1524463520741}
     *
     * @return
     */
    @PostMapping("/test_getData")
    public ResultEntity testDataValid(@JsonParameter String name) {
        return Results.newSingleResultEntity(name);
    }
}
