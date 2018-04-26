package com.i000.stock.user.web.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:48 2018/4/25
 * @Modified By:
 */
@Component
public class StringToLocalDataTimeConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source);
    }
}
