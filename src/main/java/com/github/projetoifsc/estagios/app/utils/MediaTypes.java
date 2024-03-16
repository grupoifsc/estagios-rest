package com.github.projetoifsc.estagios.app.utils;

import org.springframework.http.MediaType;

public class MediaTypes {
	
	public static final String APPLICATION_JSON = MediaType.APPLICATION_JSON_VALUE;
	public static final String APPLICATION_XML = MediaType.APPLICATION_XML_VALUE;
	public static final String APPLICATION_YAML = "application/x-yaml";
	public static final String APPLICATION_HAL = org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
	public static final String APPLICATION_HAL_FORMS = org.springframework.hateoas.MediaTypes.HAL_FORMS_JSON_VALUE;

}
