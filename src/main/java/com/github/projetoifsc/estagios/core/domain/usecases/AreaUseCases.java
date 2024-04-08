package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IAreaRepository;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.domain.iArea;

import java.util.List;

public class AreaUseCases {

    AreaReadOperations readOperations;
    AreaWriteOperations writeOperations;
    IOrganizationRepository organizationRepository;

    public AreaUseCases(IAreaRepository areaRepository, IOrganizationRepository organizationRepository) {
        readOperations = new AreaReadOperations(areaRepository);
        writeOperations = new AreaWriteOperations(areaRepository);
        this.organizationRepository = organizationRepository;
    }

    public List<iArea> getAll() {
        return readOperations.getAll();
    }

    public iArea getById(String id) {
        return readOperations.getById(id);
    }

    public iArea create(String organizationId, iArea area) {
        var organization = organizationRepository.findById(organizationId);
        return writeOperations.create(organization, area);
    }


}
