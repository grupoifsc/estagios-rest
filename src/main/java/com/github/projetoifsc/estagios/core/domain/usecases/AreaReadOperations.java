package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IAreaRepository;
import com.github.projetoifsc.estagios.core.domain.iArea;

import java.util.List;

public class AreaReadOperations {

    IAreaRepository areaRepository;

    public AreaReadOperations(IAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<iArea> getAll() {
        return areaRepository.getAll();
    }

    public iArea getById(String id) {
        return areaRepository.getById(id);
    }

}
