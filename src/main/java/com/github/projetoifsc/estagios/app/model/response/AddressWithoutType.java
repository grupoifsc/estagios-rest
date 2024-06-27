package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"rua", "bairro", "cidade", "estado", "pais"})
public class AddressWithoutType extends Address {

    @JsonIgnore
    private String id;

    @JsonProperty(value = "tipo")
    @JsonIgnore
    private String type;

    @Override
    @JsonIgnore
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    @JsonIgnore
    public String getType() {
        return null;
    }

    @Override
    public void setType(String type) { }


}
