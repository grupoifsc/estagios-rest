package com.github.projetoifsc.estagios.app.utils.serialization;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.projetoifsc.estagios.app.utils.MediaTypes;

public class YAMLJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter{

	public YAMLJackson2HttpMessageConverter() { // TODO Entender sobre Jackson Converter e serialization
		super(new YAMLMapper()
				.setSerializationInclusion( // Tipo de dados que serão serializados
						JsonInclude.Include.USE_DEFAULTS), // Dados não nulos
				MediaType.parseMediaType( MediaTypes.APPLICATION_YAML ) // TODO Entender sobre MediaTye.parseMediaType()
			);
	}

}
