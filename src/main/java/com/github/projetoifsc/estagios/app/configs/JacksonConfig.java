package com.github.projetoifsc.estagios.app.configs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;

@Configuration
class JacksonConfig {

//    @Bean
//    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//        System.out.println("TÁ PASSANDO POR AQUI???");
//        return new Jackson2ObjectMapperBuilder()
//                .serializers(LocalDateTimeSerializer.INSTANCE)
//                .serializers(LocalDateSerializer.INSTANCE)
//                .deserializers(LocalDateTimeDeserializer.INSTANCE)
//                .deserializers(LocalDateDeserializer.INSTANCE)
//                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
//                //.serializationInclusion(JsonInclude.Include.NON_NULL)
//                ;
//    }

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper() {
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
//
//        Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();
//
//        return new ObjectMapper()
//                .registerModule(javaTimeModule)
//                .registerModule(hibernate5JakartaModule)
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    }

}
