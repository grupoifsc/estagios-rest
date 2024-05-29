package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.model.shared.ContactView;

public class ContactPublicView extends ContactView {

    @JsonIgnore
    private String id;

}
