package com.github.projetoifsc.estagios.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonParser {

    private final ObjectMapper mapper;

    public JsonParser() {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(new Hibernate5JakartaModule())
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

    public String valueAsString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public <T> T stringAsObject(String str, Class<T> type) {
        try {
            return mapper.readValue(str, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void printValue(Object o) {
        try {
            System.out.println(mapper.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }


    public <T> T parseString(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
