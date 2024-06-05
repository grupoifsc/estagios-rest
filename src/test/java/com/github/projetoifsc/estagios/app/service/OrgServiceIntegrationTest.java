package com.github.projetoifsc.estagios.app.service;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.response.PublicAddressResponse;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicOrgProfileResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.infra.db.jpa.GeradorCnpj;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class OrgServiceIntegrationTest {

    @Autowired
    OrgService orgService;

    @Autowired
    Mapper mapper;

    @Autowired
    JsonParser jsonParser;

    OrgMocker mocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());

    UserPrincipal userPrincipal;
    IOrganization entity;


    @BeforeEach
    void setUp() {
        var mockedEntity = mocker.generateWithIdAsZero();
        var view = mapper.map(mockedEntity, NewOrgProfileRequest.class);
        entity = orgService.create(view);
        userPrincipal = new UserPrincipal(entity.getId(), null,null, null);
    }

    @Test
    void getPrivateProfile() {
        var dto = orgService.getAuthUserPerfil(userPrincipal, entity.getId());
        jsonParser.printValue(dto);
        

        var mapped = mapper.map(dto, PrivateOrgProfileResponse.class);
        jsonParser.printValue(mapped);

    }

    @Test
    void getPublic() {
        var dto = orgService.getUserPublicProfile(userPrincipal, entity.getId());
        jsonParser.printValue(dto);

        var mapped = mapper.map(dto, PublicOrgProfileResponse.class);
        jsonParser.printValue(mapped);

    }


    @Test
    void save() {

        var mainAddr = new PublicAddressResponse();
        mainAddr.setBairro("Rio Vermelho - Atualizado");
        mainAddr.setCidade("Fpolis");
        mainAddr.setPais("Brasil");
        mainAddr.setEstado("SC");
        mainAddr.setRua("Anthonio Thiago Nunes");

        var org = new NewOrgProfileRequest();
        //org.setId("63");
        org.setNome("Ju Atualizado");
        org.setMainAddress(mainAddr);
        org.setIe(true);
        org.setInfo("etc etc etc");
        org.setRedesSociais("minhas redes");
        org.setWebsite("www.meusite.com");

        System.out.println("Imprimindo o objeto local: ");
        jsonParser.printValue(org);

        var saved = orgService.create(org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        jsonParser.printValue(saved);

        var mapped = mapper.map(saved, PrivateOrgProfileResponse.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        jsonParser.printValue(mapped);

    }


    @Test
    void delete() {

        orgService.deleteAuthUserPerfil(userPrincipal, entity.getId());

    }


    @Test
    void update() {

        var org = new NewOrgProfileRequest();
        org.setId(entity.getId());
        org.setNome("Ju Via Update");
        org.setIe(true);
        org.setInfo("updated etc etc etc");
        org.setRedesSociais("updated minhas redes");
        org.setWebsite("www.meusite.com");

        System.out.println("Imprimindo o objeto local: ");
        jsonParser.printValue(org);

        var saved = orgService.updateAuthUserPerfil(userPrincipal, org.getId(), org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        jsonParser.printValue(saved);

        var mapped = mapper.map(saved, PrivateOrgProfileResponse.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        jsonParser.printValue(mapped);

    }


}
