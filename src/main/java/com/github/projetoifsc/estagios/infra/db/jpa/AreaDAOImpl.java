package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IArea;
import com.github.projetoifsc.estagios.core.IAreaDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class AreaDAOImpl implements IAreaDAO {

    AreaRepository areaRepository;

    @Autowired
    public AreaDAOImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<IArea> getAll() {
        var areas = areaRepository.findAll();
        return areas.stream().map(area -> (IArea) area).toList();
    }

    @Override
    public IArea getById(String id) {
        return areaRepository.findById(Long.parseLong(id))
                .orElseThrow(EntityNotFoundException::new);
    }
    
}
