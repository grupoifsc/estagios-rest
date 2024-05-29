package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.model.shared.AddressView;

public class AdressPublicView extends AddressView {

    @JsonIgnore
    private String id;

}
