package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IAreaUseCases {

    List<IArea> getAll();
    IArea getById(String id);

}
