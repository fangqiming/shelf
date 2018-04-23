package com.i000.stock.user.core.exception;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 10:52 2018/4/23
 * @Modified By:
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 6717535949156142526L;

    /**
     * 错误码
     */
    private Long code;

    BaseException(Long code, String message) {
        super(message);
        this.code = code;
    }

    BaseException(Long code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
