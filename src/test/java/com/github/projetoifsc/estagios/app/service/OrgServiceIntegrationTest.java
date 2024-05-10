package com.github.projetoifsc.estagios.app.service;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.utils.mock.OrgMock;
import com.github.projetoifsc.estagios.app.view.AddressView;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.app.view.OrgPublicProfileBasicView;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.general.config.LocalMapper;
import com.github.projetoifsc.estagios.infra.db.jpa.GeradorCnpj;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class OrgServiceIntegrationTest {

    @Autowired
    OrgService orgService;

   // @Autowired
    ModelMapper modelMapper = new ModelMapper();

    IOrganization entity;

    OrgMocker mocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());


    @BeforeEach
    void setUp() {
        var mockedEntity = mocker.generateWithIdAsZero();
        var view = modelMapper.map(mockedEntity, OrgPrivateProfileBasicView.class);
        entity = orgService.create(view).getBody();
    }

    @Test
    void getPrivateProfile() {
        var dto = orgService.getAuthUserPerfil(entity.getId());
        LocalMapper.printAsJson(dto);

        var mapped = modelMapper.map(dto.getBody(), OrgPrivateProfileBasicView.class);
        LocalMapper.printAsJson(mapped);

    }

    @Test
    void getPublic() {
        var dto = orgService.getUserPublicProfile(entity.getId());
        LocalMapper.printAsJson(dto);

        var mapped = modelMapper.map(dto.getBody(), OrgPublicProfileBasicView.class);
        LocalMapper.printAsJson(mapped);

    }


    @Test
    void save() {

        var mainAddr = new AddressView();
        mainAddr.setBairro("Rio Vermelho - Atualizado");
        mainAddr.setCidade("Fpolis");
        mainAddr.setPais("Brasil");
        mainAddr.setEstado("SC");
        mainAddr.setRua("Anthonio Thiago Nunes");

        var org = new OrgPrivateProfileBasicView();
        //org.setId("63");
        org.setNome("Ju Atualizado");
        org.setMainAddress(mainAddr);
        org.setIe(true);
        org.setInfo("etc etc etc");
        org.setRedesSociais("minhas redes");
        org.setWebsite("www.meusite.com");

        System.out.println("Imprimindo o objeto local: ");
        LocalMapper.printAsJson(org);

        var saved = orgService.create(org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        LocalMapper.printAsJson(saved);

        var mapped = modelMapper.map(saved.getBody(), OrgPrivateProfileBasicView.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        LocalMapper.printAsJson(mapped);

    }


    @Test
    void delete() {

        orgService.deleteAuthUserPerfil(entity.getId());

    }


    @Test
    void update() {

        var org = new OrgPrivateProfileBasicView();
        org.setId(entity.getId());
        org.setNome("Ju Via Update");
        org.setIe(true);
        org.setInfo("updated etc etc etc");
        org.setRedesSociais("updated minhas redes");
        org.setWebsite("www.meusite.com");

        System.out.println("Imprimindo o objeto local: ");
        LocalMapper.printAsJson(org);

        var saved = orgService.updateAuthUserPerfil(org.getId(), org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        LocalMapper.printAsJson(saved);

        var mapped = modelMapper.map(saved.getBody(), OrgPrivateProfileBasicView.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        LocalMapper.printAsJson(mapped);

    }

}
