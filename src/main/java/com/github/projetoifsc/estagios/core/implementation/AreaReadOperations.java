package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaDAO;
import com.github.projetoifsc.estagios.core.models.IArea;

import java.util.List;

class AreaReadOperations {

    IAreaDAO areaRepository;

    public AreaReadOperations(IAreaDAO areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<IArea> getAll() {
        return areaRepository.getAll();
    }

    public IArea getById(String id) {
        return areaRepository.getById(id);
    }

}
