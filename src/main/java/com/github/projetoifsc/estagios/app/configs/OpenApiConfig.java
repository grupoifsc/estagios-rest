package com.github.projetoifsc.estagios.app.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BASE_URL = "/api/v1";

	public static final String AUTH = "Autenticação";
	public static final String PERFIL = "Seu Perfil";
	public static final String MODERACAO = "Moderação";
    public static final String ORGS = "Organizações";
    public static final String VAGAS = "Vagas";
    public static final String AREAS = "Áreas";

	@Bean
	public GroupedOpenApi vagas() {
		var basePackName = "com.github.projetoifsc.estagios.app.controller.";
		String[] vagas = {basePackName + "vagas"};
		return GroupedOpenApi.builder().group("vagas").packagesToScan(vagas)
				.build();
	}


	@Bean
	public GroupedOpenApi orgs() {
		var basePackName = "com.github.projetoifsc.estagios.app.controller.";
		String[] orgs = {basePackName + "orgs"};
		return GroupedOpenApi.builder().group("organizations").packagesToScan(orgs)
				.build();
	}

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
                .addServersItem(new Server().url("https://julia4453.c44.integrator.host/"))
				.addServersItem(new Server().url("http://julia4453.c44.integrator.host/"))
				.addServersItem(new Server().url("http://localhost:8080/"))
				.addServersItem(new Server().url("http://localhost:8081/"))
//				.addTagsItem( new Tag()
//						.name(AUTH)
//						.description("Autenticação e autorização")
//				)
//				.addTagsItem( new Tag()
//						.name(PERFIL)
//						.description("Perfil do usuário autenticado")
//				)
//				.addTagsItem( new Tag()
//						.name(MODERACAO)
//						.description("Moderação de vagas")
//				)
//				.addTagsItem( new Tag()
//						.name(AREAS)
//						.description("Ver Áreas de Vagas")
//				)
//				.addTagsItem( new Tag()
//						.name(ORGS)
//						.description("Perfis de organizações cadastradas")
//				)
//				.addTagsItem( new Tag()
//						.name(VAGAS)
//						.description("Criação de Vagas")
//				);

			;
	}

}
