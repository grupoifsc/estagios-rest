package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface iAddressAndContactRepository {

    iAddress findAddressById(String id);
    iContact findContactById(String id);

    //  TODO Tem que ter endpoints para os getAll endereço e contato!
    //  E lógica de negócio proibindo o acesso de terceiros

    iAddress getMainAddress(String organizationId);
    List<iAddress> getAllAddress(String organizationId);

    iContact getGeneralContact(String organizationId);
    iContact getDefaultContact(String organizationId);
    List<iContact> getAllContacts(String organizationId);

}
