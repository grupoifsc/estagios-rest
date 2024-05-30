package com.github.projetoifsc.estagios.app.configs;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.projetoifsc.estagios.app.utils.MediaTypes;
import com.github.projetoifsc.estagios.app.utils.serialization.YAMLJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType YAML = MediaType.valueOf(MediaTypes.APPLICATION_YAML);
	private static final MediaType HAL = MediaType.valueOf(MediaTypes.APPLICATION_HAL);
	private static final MediaType HAL_FORMS = MediaType.valueOf(MediaTypes.APPLICATION_HAL_FORMS);

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) { // estende a lista de conversores normais que já está usando
		converters.add(new YAMLJackson2HttpMessageConverter());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
		// Via EXTENSION. http://localhost:8080/api/person/v1.xml DEPRECATED on SpringBoot 2.6
		
		// Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
		/*
		configurer.favorParameter(true)
			.parameterName("mediaType").ignoreAcceptHeader(true)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		// Via HEADER PARAM. http://localhost:8080/api/person/v1
		
		configurer.favorParameter(false)     // TODO Entender melhor essa configuração aqui de Content Negotiation
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", YAML)
			.mediaType("hal", HAL)
			.mediaType("hal-forms", HAL_FORMS)
			;
		
	}

}