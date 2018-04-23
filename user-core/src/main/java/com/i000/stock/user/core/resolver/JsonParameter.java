package com.i000.stock.user.core.resolver;


import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * post请求中携带的参数,使用此注解可提取参数,配合JsonParameterResolver使用,若未使用JsonParameterResolver,则无效
 *
 * @Authro qygu.
 * @Email qiyao.gu@qq.com.
 * @Date 2017/3/13.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParameter {

    /**
     * 参数名, 默认为空字符串
     *
     * @return 参数名
     */
    String value() default StringUtils.EMPTY;

    /**
     * 是否为必须填参数, 默认false
     *
     * @return true | false
     */
    boolean required() default false;
}