package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.IArea;

import java.util.List;

class AreaUseCases {

    AreaReadOperations readOperations;
    AreaWriteOperations writeOperations;
    IOrganizationRepository organizationRepository;

    public AreaUseCases(IAreaRepository areaRepository, IOrganizationRepository organizationRepository) {
        readOperations = new AreaReadOperations(areaRepository);
        writeOperations = new AreaWriteOperations(areaRepository);
        this.organizationRepository = organizationRepository;
    }

    public List<IArea> getAll() {
        return readOperations.getAll();
    }

    public IArea getById(String id) {
        return readOperations.getById(id);
    }

    public IArea create(String organizationId, IArea area) {
        var organization = organizationRepository.findById(organizationId);
        return writeOperations.create(organization, area);
    }


}
