package com.github.projetoifsc.estagios.app.service;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.utils.mock.OrgMock;
import com.github.projetoifsc.estagios.app.view.AddressView;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.app.view.OrgPublicProfileBasicView;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.infra.db.jpa.GeradorCnpj;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import com.github.projetoifsc.estagios.utils.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    JsonParser jsonParser = new JsonParser();

    IOrganization entity;

    OrgMocker mocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());


    @BeforeEach
    void setUp() {
        var mockedEntity = mocker.generateWithIdAsZero();
        var view = modelMapper.map(mockedEntity, OrgPrivateProfileBasicView.class);
        entity = orgService.create(view);
    }

    @Test
    void getPrivateProfile() {
        var dto = orgService.getAuthUserPerfil(entity.getId());
        jsonParser.printValue(dto);
        

        var mapped = modelMapper.map(dto, OrgPrivateProfileBasicView.class);
        jsonParser.printValue(mapped);

    }

    @Test
    void getPublic() {
        var dto = orgService.getUserPublicProfile(entity.getId());
        jsonParser.printValue(dto);

        var mapped = modelMapper.map(dto, OrgPublicProfileBasicView.class);
        jsonParser.printValue(mapped);

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
        jsonParser.printValue(org);

        var saved = orgService.create(org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        jsonParser.printValue(saved);

        var mapped = modelMapper.map(saved, OrgPrivateProfileBasicView.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        jsonParser.printValue(mapped);

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
        jsonParser.printValue(org);

        var saved = orgService.updateAuthUserPerfil(org.getId(), org);
        System.out.println("O que veio salvo lá do banco de dados: ");
        jsonParser.printValue(saved);

        var mapped = modelMapper.map(saved, OrgPrivateProfileBasicView.class);
        System.out.println("O objeto mapeado para ser devolvido: ");
        jsonParser.printValue(mapped);

    }

}
