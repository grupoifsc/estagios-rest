package com.github.projetoifsc.estagios.app.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BASE_URL = "/api/v1";
    public static final String ORGS = "Organizações";
    public static final String VAGAS = "Vagas";
    public static final String AREAS = "Áreas";
	public static final String AUTH = "Autenticação";


    @Bean
    public OpenAPI customOpenApi() {
		return new OpenAPI()
				.components(
			new Components()
					.addSecuritySchemes(AUTHORIZATION,
							new SecurityScheme()
									.name(AUTHORIZATION)
									.type(SecurityScheme.Type.HTTP)
									.scheme("bearer")
									.bearerFormat("JWT")
									.in(SecurityScheme.In.HEADER)
					)
				)
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
						.name(AREAS)
						.description("")
				)
				.addTagsItem( new Tag()
						.name(ORGS)
						.description("")
				)
				.addTagsItem( new Tag()
						.name(VAGAS)
						.description("")
				)
				.addTagsItem( new Tag()
						.name(AUTH)
						.description("")
				);
	}

}
