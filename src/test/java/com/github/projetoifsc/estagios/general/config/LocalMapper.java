package com.github.projetoifsc.estagios.general.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;

public class LocalMapper {

    static JsonMapper jsonMapper = JsonMapper
            .builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    public static ObjectMapper getMapper() {
        return jsonMapper;
    }

    public static void printAsJson(Object o) {
        try {
            System.out.println(jsonMapper.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <T> T readValue(String strContent, Class<T> type) {
        try {
            return jsonMapper.readValue(strContent, type);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
