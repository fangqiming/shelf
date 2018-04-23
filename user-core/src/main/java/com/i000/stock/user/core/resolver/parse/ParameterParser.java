package com.i000.stock.user.core.resolver.parse;

import org.springframework.core.MethodParameter;

import java.io.IOException;

public interface ParameterParser {

    /**
     * 参数解析
     *
     * @param body            请求体数据
     * @param key             需要解析的数据部分
     * @param methodParameter 方法帮助对象
     * @return 解析后的参数
     * @throws IOException io exception
     */
    Object parse(String body, String key, MethodParameter methodParameter) throws IOException;
}