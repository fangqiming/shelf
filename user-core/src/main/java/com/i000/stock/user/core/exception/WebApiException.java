package com.i000.stock.user.core.exception;


import com.i000.stock.user.core.constant.enums.BaseEnum;

/**
 * @Authro qygu.
 * @Email qiyao.gu@qq.com.
 * @Date 2017/6/15.
 */
public class WebApiException extends BaseException {

    private static final long serialVersionUID = 5732902761584860364L;

    public WebApiException(Long code, String message) {
        super(code, message);
    }

    public WebApiException(BaseEnum baseEnum) {
        this(baseEnum.getCode(), baseEnum.getMsg());
    }
}