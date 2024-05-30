package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgBasicInfoProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgPublicProfileProjection;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrganizationRepositoryUnitTest {

    @Autowired
    OrganizationRepository repository;

    Faker faker = new Faker();
    GeradorCnpj geradorCnpj = new GeradorCnpj();
    OrgMocker orgMocker = new OrgMocker(faker, geradorCnpj);

    @Autowired
    JsonParser jsonParser;

    @Autowired
    Mapper mapper;


    OrganizationEntity entity;


    @Test
    @Transactional
    @Rollback(value = false)
    void saveOrganization() {
        entity = orgMocker.generate();
        var saved = repository.save(entity);
        jsonParser.printValue(saved);
    }


    @Test
    @Transactional
    void updateOrganization() {
        entity = repository.save(orgMocker.generate());
        jsonParser.printValue(entity);
        var id = entity.getId();
        var newData = orgMocker.generate();
        newData.setId(id);
        var updated = repository.save(newData);
        jsonParser.printValue(updated);
    }

    @Test
    @Transactional
    void findAllProjectedBy() {
        var responseProjection = repository.findAllProjectedBy(PageRequest.of(0, 20), OrgPublicProfileProjection.class);
        jsonParser.printValue(responseProjection.getContent());
    }


    @Test
    @Transactional
    void findById() {
        var projection = repository.findById(195L, OrganizationEntity.class);
        var org = projection.orElse(null);
        jsonParser.printValue(org);
    }


    // TODO: Otimizar o Delete pois não é otimizado! Ele busca todas as relações e depois deleta elas
    // Ver esta solução possível: https://vladmihalcea.com/cascade-delete-unidirectional-associations-spring/
    // Cuidar com as relações de JobEntity também (tem que usar um select de qualquer forma)
    @Test
    void delete() {
        repository.deleteById(381L);
    }


    @Test
    void findAllProjectedByByIe() {
        var orgs = repository.findAllByIe(true, PageRequest.of(0, 10), OrgPublicProfileProjection.class);
        jsonParser.printValue(orgs.getContent());
    }


    @Test
    void findAllProjectedByByIdIn() {
        var orgs = repository.findAllByIdIn (List.of(196L, 200L, 270L), OrgPrivateProfileProjection.class);
        jsonParser.printValue(orgs);
    }


    @Test
    void findAllProjectedByByExclusiveReceivedJobsId() {
        var orgs = repository.findAllByExclusiveReceivedJobsId(4L, OrgBasicInfoProjection.class);
        jsonParser.printValue(orgs);
    }


    @Test
    @Transactional
    void findByUserCredentialsEmail() {
        var email = "teste@teste.com";
        var org_id = "395";
       // var user_id = 3L;

        var org = repository.findByUserCredentialsEmail(email, OrganizationEntity.class).orElseThrow();
        jsonParser.printValue(org.getUserCredentials());
        jsonParser.printValue(org.getId());
        assertEquals(org.getId(), org_id);
    }

    @Test
    @Transactional
    void findByUserCredentialsId() {
     //   var email = "teste@teste.com";
        var org_id = "395";
        var user_id = 3L;

        var org = repository.findByUserCredentialsId(user_id, OrganizationEntity.class).orElseThrow();
        var credentials = org.getUserCredentials();
        jsonParser.printValue(credentials);
        //jsonParser.printValue(org.getUserCredentials());
        jsonParser.printValue(org.getId());
        assertEquals(org.getId(), org_id);
    }


}
