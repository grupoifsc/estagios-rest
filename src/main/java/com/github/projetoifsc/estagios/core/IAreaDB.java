package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAreaDB {

    List<IArea> getAll();
    IArea getById(String id);

}
