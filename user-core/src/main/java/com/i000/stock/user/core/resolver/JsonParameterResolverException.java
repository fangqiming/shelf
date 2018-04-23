package com.i000.stock.user.core.resolver;

public class JsonParameterResolverException extends RuntimeException {

    private static final long serialVersionUID = 23574450313357357L;

    public JsonParameterResolverException(String message) {
        super(message);
    }

    public JsonParameterResolverException(Throwable cause) {
        super(cause);
    }

    public JsonParameterResolverException(String message, Throwable cause) {
        super(message, cause);
    }
}