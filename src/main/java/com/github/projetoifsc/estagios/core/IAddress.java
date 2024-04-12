package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAddress {

    boolean isMain();
    String getId();
    IOrganization getOwner();

    interface IAreaUseCases {
        List<IArea> getAll();

        IArea getById(String id);

        IArea create(String organizationId, IArea area);
    }
}
