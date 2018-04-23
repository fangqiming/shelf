package com.i000.stock.user.core.exception;


import com.i000.stock.user.core.constant.enums.BaseEnum;

public class ServiceException extends BaseException {

    private static final long serialVersionUID = -4434239688684421080L;

    public ServiceException(Long code, String message) {
        super(code, message);
    }

    public ServiceException(BaseEnum baseEnum) {
        this(baseEnum.getCode(), baseEnum.getMsg());
    }

    public ServiceException(Long code, String message, Throwable cause) {
        super(code, message, cause);
    }
}