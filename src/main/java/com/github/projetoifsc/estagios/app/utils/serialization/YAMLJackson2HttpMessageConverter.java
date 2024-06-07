package com.github.projetoifsc.estagios.app.utils.serialization;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;

public class YAMLJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter{

	public YAMLJackson2HttpMessageConverter() {
		super(new YAMLMapper()
				.setSerializationInclusion(
						JsonInclude.Include.USE_DEFAULTS),
				MediaType.parseMediaType( MediaTypes.APPLICATION_YAML )
			);
	}

}
