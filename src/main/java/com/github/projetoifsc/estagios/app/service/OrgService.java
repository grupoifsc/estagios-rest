package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.interfaces.INewUser;
import com.github.projetoifsc.estagios.app.model.request.NewUserRequest;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfileResponse;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.model.response.OrgPublicProfileBasicInfoView;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


// TODO:
// Chain Of Responsibility:
// UserRequestHandler()
// a) Valida dados de autenticação
// b) Autentica
// c) Autoriza
// d) Valida dados de entrada
// e) Tira o HATEOAS
// Fim do Handler
// f) Chama outra camada (AQUI ENTRA CÓDIGO ESPECÍFICO)
// g) Aplica HATEOAS na resposta (TBM EH ESPECÍFICO)
// h) Devolve a resposta ao Controller


@Service
public class OrgService {

    private final IOrganizationUseCases organizationUseCases;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    OrgService(IOrganizationUseCases organizationUseCases, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.organizationUseCases = organizationUseCases;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    public IOrganization create(INewUser newUser) {
        var userData = (NewUserRequest) newUser;
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        var createdOrganization = organizationUseCases.createProfile(userData);
        return mapper.map(createdOrganization, OrgPrivateProfileResponse.class);
    }


    public IOrganization getAuthUserPerfil(String id) {
        var org = organizationUseCases.getPrivateProfile(id, id);
        return mapper.map(org, OrgPrivateProfileResponse.class);
    }


    public IOrganization getUserPublicProfile(String id) {
        var org = organizationUseCases.getPublicProfile(id, id);
        return mapper.map(org, OrgPublicProfileBasicInfoView.class);
    }


    public IOrganization updateAuthUserPerfil(String id, INewUser updatedUser) {
        var org = organizationUseCases.updateProfile(id,id, updatedUser);
        return mapper.map(org, OrgPrivateProfileResponse.class);
    }


    public void deleteAuthUserPerfil(String id) {
        organizationUseCases.deleteProfile(id, id);
    }


    public Page<IOrganization> getAllSchools() {
        var orgs = organizationUseCases.getSchools();
        return orgs.map(
                org -> mapper.map(org, OrgPublicProfileBasicInfoView.class)
        );
    }


}
