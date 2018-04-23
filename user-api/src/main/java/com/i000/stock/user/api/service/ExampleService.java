package com.i000.stock.user.api.service;

import com.i000.stock.user.dao.model.Example;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:19 2018/4/23
 * @Modified By:
 */
public interface ExampleService {

    /**
     * 根据主键获取对象
     *
     * @param id
     * @return
     */
    Example get(Long id);

}
