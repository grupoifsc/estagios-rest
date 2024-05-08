package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAddressAndContactDB {

    IAddress findAddressById(String id);
    IContact findContactById(String id);

    //  TODO Tem que ter endpoints para os getAll endereço e contato!
    //  E lógica de negócio proibindo o acesso de terceiros

    IAddress getMainAddress(String organizationId);
    List<IAddress> getAllAddress(String organizationId);
    IContact getGeneralContact(String organizationId);
    IContact getDefaultContact(String organizationId);
    List<IContact> getAllContacts(String organizationId);

}
