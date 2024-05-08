package com.github.projetoifsc.estagios.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONStringer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

class MapperTest {

    interface MinimumData {
        //String getName();
        void setName(String name);
    }

    public static class MyEntity implements MinimumData {

        private String nome;

        public MyEntity() {

        }

        //@Override
        String getName() {
            return nome;
        }

        @Override
        public void setName(String name) {
            this.nome = name;
        }

        @Override
        public String toString() {
            return "MyEntity{" +
                    "nome='" + nome + '\'' +
                    '}';
        }
    }

    public static class MyAppDTO implements MinimumData, Serializable {

        private String nomeCompleto;

        public MyAppDTO() {
        }

        @JsonProperty(value = "nome_completo")
        //@Override
        public String getName() {
            return nomeCompleto;
        }

        @Override
        public void setName(String name) {
            this.nomeCompleto = name;
        }

        @Override
        public String toString() {
            return "MyAppDTO{" +
                    "nomeCompleto='" + nomeCompleto + '\'' +
                    '}';
        }

    }

    ModelMapper mapper = new ModelMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    MyAppDTO appObject;
    MyEntity dbObject;

    @BeforeEach
    void setUp() {
        appObject = new MyAppDTO();
        dbObject = new MyEntity();
    }

    @Test
    void appToEntity () {
        appObject.setName("Juliana Hachmann");
        var mapped = mapper.map(appObject, MyEntity.class);
        System.out.println(mapped);
    }

    @Test
    void entityToApp() throws JsonProcessingException {
        dbObject.setName("Juana Racham");
        var mapped = mapper.map(dbObject, MyAppDTO.class);
        System.out.println(mapped);
        System.out.println(objectMapper.writeValueAsString(mapped));

    }

}
