package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;

import static org.mockito.Mockito.*;

public class GetAllOrganizationsUnitTest {

    IOrganizationRepository organizationRepository = mock();
    GetAllOrganizations service = new GetAllOrganizations(organizationRepository);



}
