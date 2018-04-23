package com.i000.stock.user.core.constant.enums;


import lombok.Getter;

/**
 * @Author:qmfang
 * @Description: 异常信息枚举
 * @Date:Created in 10:44 2018/4/23
 * @Modified By:
 */
public enum ApplicationErrorMessage implements BaseEnum {

    NO_PERMISSION(11090001L, "无权限"),
    SERVER_ERROR(11090002L, "系统内部异常"),
    INVALID_PARAMETER(11090003L, "缺少参数或参数错误"),
    NOT_EXISTS(11090004L, "请求的数据不存在");

    @Getter
    private Long code;

    @Getter
    private String msg;

    ApplicationErrorMessage(Long code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
