package com.github.projetoifsc.estagios.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.projetoifsc.estagios.app.utils.swagger.SwaggerTags;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

	
    @Bean
    public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info() 
					.title("API para Vagas de Estágio")
					.version("0.1.0")
					.description("Um serviço web para conectar empresas e instituições de ensino em torno de um objetivo em comum: divulgar vagas de estágio à comunidade estudantil. Em desenvolvimento como Projeto Integrador para conclusão do Curso Técnico em Desenvolvimento de Sistemas - Florianópolis/IFSC - 2024.1")
					.license(new License()
							.name("Apache 2.0")
							.url("URL"))
					.termsOfService("URL")
					.contact(new Contact()
								.url(""))
					.summary("Um serviço web para conectar empresas e instituições de ensino em torno de um objetivo em comum: divulgar vagas de estágio à comunidade estudantil")
				)
				.addTagsItem( new Tag()
						.name(SwaggerTags.ORGS)
						.description("")
				)
				.addTagsItem( new Tag()
						.name(SwaggerTags.VAGAS)
						.description("")
				)

				;
	}

}
