package com.i000.stock.user.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:09 2018/4/23
 * @Modified By:
 */
@Component
public class Long2LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        Long time = Long.parseLong(source);
        return Instant.ofEpochSecond(time).atOffset(ZoneOffset.of("+08:00")).toLocalDateTime();
    }
}
