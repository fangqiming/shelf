package com.i000.stock.user.service.impl;

import com.i000.stock.user.api.service.ExampleService;
import com.i000.stock.user.dao.mapper.ExampleMapper;
import com.i000.stock.user.dao.model.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:22 2018/4/23
 * @Modified By:
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Resource
    private ExampleMapper exampleMapper;

    @Override
    public Example get(Long id) {
        return exampleMapper.selectById(id);
    }
}
