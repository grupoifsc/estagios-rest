package com.github.projetoifsc.estagios.app.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TesteController {

    @PostMapping("/teste")
    public Pessoa pessoa(
            @RequestBody Pessoa pessoa
    ) {
        return pessoa;
    }

    @GetMapping("/teste")
    public Pessoa getPessoa(
    ) {
        var pessoa = new Pessoa();
        pessoa.setDate(LocalDateTime.now());
        pessoa.setLastName("sobrenome");
        return pessoa;
    }



    public static class Pessoa {

        private String name;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String email;

//        @JsonProperty("last_name")
        private String lastName;

        private LocalDateTime date;

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
