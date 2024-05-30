package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAreaDAO {

    List<IArea> getAll();
    IArea getById(String id);

}
