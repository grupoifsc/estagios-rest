package com.github.projetoifsc.estagios.app.service;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.request.NewVagaRequest;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.infra.db.jpa.JobMocker;
import com.github.projetoifsc.estagios.utils.JsonParser;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VagaServiceIntegrationTest {

    @Autowired
    VagaService service;

    @Autowired
    Mapper mapper;

    @Autowired
    JsonParser jsonParser;

    JobMocker jobMocker = new JobMocker(new Faker());

    IJob job;

    @Test
    void create() {
        var entryData = mapper.map(jobMocker.generate(), NewVagaRequest.class);
//        entryData.setReceiversIds(List.of("273", "274"));
//        entryData.setAreasIds(List.of("1", "2", "50"));
//        entryData.setContactId("69");
//        entryData.setAddressId("97");
        var created = service.create(userPrincipal, entryData);
        assertFalse(created.getId().equalsIgnoreCase("0"));
        jsonParser.printValue(created);
    }

    @Test
    void update() {
        var entryData = mapper.map(jobMocker.generate(), NewVagaRequest.class);
        String id = "29";
        var updated = service.update(userPrincipal, id, entryData);
        assertTrue(updated.getId().equalsIgnoreCase(id));
        assertNotEquals(updated.getCreatedAt(), updated.getUpdatedAt());
        jsonParser.printValue(updated);
    }


    @Test
    void delete() {
        String id = "18";
        assertDoesNotThrow(() -> service.delete(userPrincipal, id));
        assertThrows(Exception.class, () -> service.delete(userPrincipal, "300"));
    }


    @Test
    void getOnePrivate() {
        String id = "29";
        var vaga = service.getPrivateProfile(userPrincipal, id);
        jsonParser.printValue(vaga);

    }


    @Test
    void getOnePublic() {
        String id = "4";
        var vaga = service.getPublicProfile(userPrincipal, id);
        jsonParser.printValue(vaga);
    }


    @Test
    void getAllCreated() {
        String id = "195";
        var vagas = service.getAllCreatedByUser(userPrincipal, id, 0, 50);
        jsonParser.printValue(vagas.getContent());
    }



    @Test
    void getAllReceived() {
        String id = "274";
        var vagas = service.getAllReceivedByUser(userPrincipal, id, new HashMap<>());
        System.out.println(vagas.size());
        vagas.forEach(v -> System.out.println(v.getId()));
        jsonParser.printValue(vagas);
    }

}
