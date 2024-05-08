package com.github.projetoifsc.estagios;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ju")
class NomeConfig {

    private String nome;
    private String sobrenome;

    String getNome() {
        return nome;
    }

    String getSobrenome() {
        return sobrenome;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}
