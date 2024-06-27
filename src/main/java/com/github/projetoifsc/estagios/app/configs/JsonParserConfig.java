package com.github.projetoifsc.estagios.app.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonParserConfig {

    @Bean
    public JsonParser defaultJsonMapper() {
        return new JsonParser();
    }

}
