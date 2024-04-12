package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAreaRepository {

    IArea create(IArea area);
    IOrganization getOwner(IArea area);
    List<IArea> getAll();
    IArea getById(String id);

}
