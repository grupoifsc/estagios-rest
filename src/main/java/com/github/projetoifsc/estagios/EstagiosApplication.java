package com.github.projetoifsc.estagios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class EstagiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstagiosApplication.class, args);
    }

}
