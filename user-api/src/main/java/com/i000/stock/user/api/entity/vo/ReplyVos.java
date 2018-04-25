package com.i000.stock.user.api.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 9:51 2018/4/25
 * @Modified By:
 */
@Data
public class ReplyVos {
    private ReplyVo reply;
    private List<ReplyVos> son;
}
