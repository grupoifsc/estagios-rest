package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface IAreaRepository {

    iArea create(iArea area);
    IOrganization getOwner(iArea area);

    List<iArea> getAll();

    iArea getById(String id);
}
