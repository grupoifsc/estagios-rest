package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.projetoifsc.estagios.app.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.model.response.AdressPublicView;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfileResponse;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrgEntityCascadeAllTest {

    JsonMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    OrgService orgService;

    @Autowired
    private OrganizationRepository organizationRepository;

    public OrgEntityCascadeAllTest() {
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());

    }

    @Test
    void saveCascadingAll() {

        var mainAddr = new AdressPublicView();
        mainAddr.setBairro("Kobrasol");
        mainAddr.setCidade("São José");
        mainAddr.setPais("Brasil");
        mainAddr.setEstado("SC");
        mainAddr.setRua("Av. Central");

        var org = new OrgPrivateProfileResponse();
        org.setNome("Nana Vasconcelos");
        org.setMainAddress(mainAddr);
        org.setIe(true);
        org.setInfo("etc etc etc");
        org.setRedesSociais("minhas redes");
        org.setWebsite("www.meusite.com");

        System.out.println("Imprimindo o objeto local: ");
        try {
            System.out.println(mapper.writeValueAsString(org));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        var mapped = modelMapper.map(org, OrganizationEntity.class);

        var saved = organizationRepository.save(mapped);
        long id = Long.parseLong(saved.getId());

        var retrieved = organizationRepository.findById(id, OrgPrivateProfileProjection.class);

        System.out.println("O que veio salvo lá do banco de dados: ");
        try {
            System.out.println(mapper.writeValueAsString(retrieved.orElse(null)));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

    }


}
