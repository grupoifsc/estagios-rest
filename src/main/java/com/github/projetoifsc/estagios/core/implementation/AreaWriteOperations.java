package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IArea;
import com.github.projetoifsc.estagios.core.implementation.UnauthorizedAccessException;

class AreaWriteOperations {

    private IAreaRepository areaRepository;
    public AreaWriteOperations(IAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public IArea create(IOrganization organization, IArea area) {
        if(organization.isSchool()) {
            area.setOwner(organization);
            return areaRepository.create(area);
        }
        throw new UnauthorizedAccessException("Only schools are allowed to create study areas");
    }


}
