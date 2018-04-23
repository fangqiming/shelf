package com.i000.stock.user.core.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jsonGenerator,
                          SerializerProvider serializers)
            throws IOException {
        long timestamp = Timestamp.valueOf(value).getTime();
        jsonGenerator.writeNumber(timestamp);
    }
}