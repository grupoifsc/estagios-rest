package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaDB;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IArea;

class AreaWriteOperations {

    private IAreaDB areaRepository;
    public AreaWriteOperations(IAreaDB areaRepository) {
        this.areaRepository = areaRepository;
    }

    public IArea create(IOrganization organization, IArea area) {
        if(organization.getIe()) {
            area.setOwner(organization);
            return areaRepository.create(area);
        }
        throw new UnauthorizedAccessException("Only schools are allowed to create study areas");
    }


}
