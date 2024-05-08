package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAreaDB {

    IArea create(IArea area);
    IOrganization getOwner(IArea area);
    List<IArea> getAll();
    IArea getById(String id);

}
