package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.*;

import java.util.List;

class AreaUseCases implements IAreaUseCases {

    AreaReadOperations readOperations;
    AreaWriteOperations writeOperations;
    IOrganizationRepository organizationRepository;

    public AreaUseCases(IAreaRepository areaRepository, IOrganizationRepository organizationRepository) {
        readOperations = new AreaReadOperations(areaRepository);
        writeOperations = new AreaWriteOperations(areaRepository);
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<IArea> getAll() {
        return readOperations.getAll();
    }

    @Override
    public IArea getById(String id) {
        return readOperations.getById(id);
    }

    @Override
    public IArea create(String organizationId, IArea area) {
        var organization = organizationRepository.findById(organizationId);
        return writeOperations.create(organization, area);
    }


}
