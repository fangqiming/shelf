package com.i000.stock.user.core.resolver;

import com.google.common.base.Charsets;
import com.i000.stock.user.core.resolver.helper.NativeWebRequestHelper;
import com.i000.stock.user.core.resolver.parse.JacksonParameterParser;
import com.i000.stock.user.core.resolver.parse.ParameterParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class JsonParameterResolver implements HandlerMethodArgumentResolver {

    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    private ParameterParser parameterParser = new JacksonParameterParser();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParameter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {
        checkRequestRequirementPost(webRequest);

        JsonParameter jsonParameter = parameter.getParameterAnnotation(JsonParameter.class);

        String name = getParameterName(parameter, jsonParameter);
        String body = getRequestBody(webRequest);

        if (jsonParameter.required() && StringUtils.isBlank(body)) {
            throw new JsonParameterResolverException(
                    String.format("parameter '%s' must be has value", name));
        } else if (StringUtils.isBlank(body)) {
            return null;
        }

        Object result = parameterParser.parse(body, name, parameter);
        if (jsonParameter.required() && Objects.isNull(result)) {
            throw new JsonParameterResolverException(
                    String.format("parameter '%s' must be has value", name));
        }

        return result;
    }

    /**
     * 获取参数名称
     *
     * @param parameter     parameter
     * @param jsonParameter 注解对象
     * @return 参数名
     */
    private String getParameterName(MethodParameter parameter, JsonParameter jsonParameter) {
        return Optional
                .of(jsonParameter.value())
                .filter(StringUtils::isNotBlank)
                .orElse(parameter.getParameterName());
    }

    /**
     * 获取 post 请求 body 体中的参数
     *
     * @param webRequest request封装对象
     * @return 请求体
     */
    private String getRequestBody(NativeWebRequest webRequest) {
        String body = getBody(webRequest);

        if (StringUtils.isNotBlank(body)) {
            return body;
        }

        try {
            body = parseBody(webRequest);
            putBodyToRequestScope(body, webRequest);
            return body;
        } catch (IOException e) {
            throw new JsonParameterResolverException("request body parsing fail", e);
        }
    }

    /**
     * 解析请求体
     *
     * @param nativeWebRequest request
     * @return 请求体字符串
     * @throws IOException io exception
     */
    private String parseBody(NativeWebRequest nativeWebRequest) throws IOException {
        return NativeWebRequestHelper.parseBodyAsString(nativeWebRequest, Charsets.UTF_8);
    }

    /**
     * 从request scope 中获取请求体
     *
     * @param request request
     * @return 请求体
     */
    private String getBody(NativeWebRequest request) {
        return (String) request.getAttribute(JSON_BODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
    }

    /**
     * 将请求数据压入request作用域
     *
     * @param body    请求数据
     * @param request request
     */
    private void putBodyToRequestScope(String body, NativeWebRequest request) {
        request.setAttribute(JSON_BODY_ATTRIBUTE, body, NativeWebRequest.SCOPE_REQUEST);
    }

    /**
     * 校验请求是否为 post 请求
     *
     * @param webRequest request
     */
    private void checkRequestRequirementPost(NativeWebRequest webRequest) {
        HttpServletRequest request = NativeWebRequestHelper.getHttpServletRequest(webRequest);
        if (!isPostRequest(request)) {
            throw new JsonParameterResolverException("only post request are supported!");
        }
    }

    /**
     * 判断是否为 post 请求
     *
     * @param request request
     * @return true | false
     */
    private boolean isPostRequest(HttpServletRequest request) {
        return request.getMethod().toUpperCase().equals("POST");
    }
}