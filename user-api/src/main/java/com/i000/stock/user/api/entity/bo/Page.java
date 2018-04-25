package com.i000.stock.user.api.entity.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:41 2018/4/24
 * @Modified By:
 */
@Data
public class Page<T> {

    private List<T> list;

    private Long total;
}
