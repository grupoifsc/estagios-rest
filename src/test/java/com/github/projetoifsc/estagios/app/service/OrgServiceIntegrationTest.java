package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.view.AddressView;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.app.view.OrgPublicProfileBasicView;
import com.github.projetoifsc.estagios.general.config.LocalMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrgServiceIntegrationTest {

    @Autowired
    OrgService orgService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void getPrivateProfile() {
        String id = "1";

        var dto = orgService.getAuthUserPerfil(id);
        LocalMapper.printAsJson(dto);

        var mapped = modelMapper.map(dto.getBody(), OrgPrivateProfileBasicView.class);
        LocalMapper.printAsJson(mapped);

    }

    @Test
    void getPublic() {
        String id = "1";

        var dto = orgService.getUserPublicProfile(id);
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
        org.setId("63");
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

        orgService.deleteAuthUserPerfil("1");

    }


    @Test
    void update() {

        var org = new OrgPrivateProfileBasicView();
        org.setId("63");
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
