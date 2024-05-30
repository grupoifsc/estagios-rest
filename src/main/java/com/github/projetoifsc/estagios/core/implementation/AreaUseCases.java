package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;

import java.util.List;

public class AreaUseCases implements IAreaUseCases {

    AreaReadOperations readOperations;

    public AreaUseCases(IAreaDAO areaRepository) {
        readOperations = new AreaReadOperations(areaRepository);
    }

    @Override
    public List<IArea> getAll() {
        return readOperations.getAll();
    }

    @Override
    public IArea getById(String id) {
        return readOperations.getById(id);
    }


}
