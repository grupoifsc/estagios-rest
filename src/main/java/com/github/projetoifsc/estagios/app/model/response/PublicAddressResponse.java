package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.model.shared.AddressModel;

public class PublicAddressResponse extends AddressModel {

    @JsonIgnore
    private String id;

}
