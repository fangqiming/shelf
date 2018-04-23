package com.i000.stock.user.web.aop;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class LogPointcut {

    @Pointcut("execution(public * com.i000.stock.user.web.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 返回值的日志记录
     *
     * @param value 返回值
     * @throws JSONException exception
     */
    @AfterReturning(value = "controllerAspect()", returning = "value")
    public void logRecord(Object value)
            throws JSONException {

        log.info("==========web response begin==========");
        log.info("data : {}", JSONObject.toJSON(value));
        log.info("==========web response end==========");
    }
}