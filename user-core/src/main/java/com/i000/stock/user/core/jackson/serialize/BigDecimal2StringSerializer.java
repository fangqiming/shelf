package com.i000.stock.user.core.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 用于解决BigDecimal序列化时的科学技术法问题
 */
public class BigDecimal2StringSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator,
                          SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeNumber(value.stripTrailingZeros().toPlainString());
    }
}