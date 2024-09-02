# Plataforma de Estágios

Um serviço web para conectar instituições de ensino e empresas em torno de um objetivo comum: divulgar vagas de estágio à comunidade estudantil. 

> Este projeto foi desenvolvido como Projeto de Conclusão do Curso Técnico em Desenvolvimento de Sistemas - IFSC - Florianópolis, semestre 2024.1

## Recursos

### Funcionalidades principais

- Cadastro de organizações (empresas e instituições de ensino(IEs))
- Gerenciamento de anúncios de vagas de estágio
- Moderação de vagas recebidas
- Escolha das IEs destinatárias dos anúncios

### Outros recursos

- Autenticação com JWT
- Rate Limit e buscas paginadas
- Open Source
- Formatos de dados JSON, XML, Yaml, Hal e Hal-Forms
- Swagger UI para testes de endpoints

## Instalação

**Requisitos**: 
- MySql
- Java 17+
- Maven ou sua IDE preferida

Clone este repositório
```bash
git clone https://github.com/grupoifsc/estagios-rest
```

Em seguida, acesse seu cliente MySQL preferido e crie o banco de dados para a aplicação:
```sql
CREATE DATABASE estagios;
```
Em seguida, altere o arquivo `application.yaml` deste projeto, inserindo as informações de conexão com o seu banco de dados:
```yaml
spring:
	datasource:
		url: jdbc:mysql://localhost:3306/{nome_do_banco_de_dados}
		username: {seu_usuario_mysql}
		password: {senha_mysql}
```
Para executar a aplicação, abra um terminal de comando:
```bash
# Navegue até a pasta clonada do projeto
cd caminho_para/estagios-rest

# Execute o script maven para Spring Boot
mvn spring-boot:run

# A instalação do projeto pode demorar alguns minutos e requer conexão com a internet
# Após a inicialização da aplicação Spring, acesse a Swagger UI em:
# https://localhost:8080/swagger-ui/index.html
```

Ou abra o projeto e execute com sua IDE Java favorita

## Documentação

A documentação completa do projeto pode ser acessada em: https://github.io/juhachmann/estagios-app

## Para desenvolvedores

Este projeto foi desenvolvido como um exercício de aprendizagem de construção de serviços REST com o ecossistema Spring. 

Além disto, usamos o projeto para tentar implementar alguns conceitos de estudo sobre o desenvolvimento em camadas, Arquitetura Limpa, SOLID, TDD, etc. Compreendemos que as escolhas de arquitetura do sistema estão realmente "complicando" mais do que seria o necessário. Mas foram escolhas voltadas mais para o aprendizado de conceitos do que pensando no sistema em si. 

Também estamos na fase de "refazer" os testes automatizados. Por favor, tenham paciência =)

## Tecnologias

Este sistema foi desenvolvido com as tecnologias:

- [Ecossistema Spring](https://spring.io/) (Spring Boot, Spring Web MVC, Spring Data JPA e Spring Security)
- [Springdoc](https://springdoc.org/)
- [Bucket4j](https://bucket4j.com/)
- [Auth0 JWT](https://github.com/auth0/java-jwt)
- [ModelMapper](https://modelmapper.org/)
- [FasterXML](https://github.com/FasterXML/jackson-dataformat-xml)
