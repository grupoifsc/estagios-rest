package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IArea;

import java.util.List;

class AreaReadOperations {

    IAreaRepository areaRepository;

    public AreaReadOperations(IAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<IArea> getAll() {
        return areaRepository.getAll();
    }

    public IArea getById(String id) {
        return areaRepository.getById(id);
    }

}
