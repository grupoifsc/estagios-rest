package com.github.projetoifsc.estagios.core;

import com.github.projetoifsc.estagios.core.models.IArea;

import java.util.List;

public interface IAreaUseCases {

    List<IArea> getAll();
    IArea getById(String id);

}
