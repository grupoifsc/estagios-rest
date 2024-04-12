package com.github.projetoifsc.estagios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class EstagiosApplication {

    public static void main(String[] args) {
        // Seria ótimo colocar todas as dependências em um só lugar ao invés de espalhar pelo projeto inteiro...
        SpringApplication.run(EstagiosApplication.class, args);
    }

}
