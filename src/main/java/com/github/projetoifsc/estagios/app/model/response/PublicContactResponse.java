package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.model.shared.ContactModel;

public class PublicContactResponse extends ContactModel {

    @JsonIgnore
    private String id;

}
