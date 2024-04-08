package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IAreaRepository;
import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.iArea;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

public class AreaWriteOperations {

    private IAreaRepository areaRepository;
    public AreaWriteOperations(IAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public iArea create(IOrganization organization, iArea area) {
        if(organization.isSchool()) {
            area.setOwner(organization);
            return areaRepository.create(area);
        }
        throw new UnauthorizedAccessException("Only schools are allowed to create study areas");
    }


}