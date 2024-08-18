package com.github.projetoifsc.estagios.app.service;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.infra.db.jpa.JobMocker;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    UserPrincipal userPrincipal;

    @Test
    void create() {
        var entryData = mapper.map(jobMocker.generate(), JobEntryData.class);
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
        var entryData = mapper.map(jobMocker.generate(), JobEntryData.class);
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
        userPrincipal = new UserPrincipal("427", null, null, null, null);
        String id = "90";
        var vaga = service.getPrivateProfile(userPrincipal, id);
        jsonParser.printValue(vaga);

    }


    @Test
    void getOnePublic() {
        userPrincipal = new UserPrincipal("427", null, null, null, null);
        String id = "16";
        var vaga = service.getPublicProfile(userPrincipal, id);
        jsonParser.printValue(vaga);
    }


    @Test
    void getOneDetails() {
        userPrincipal = new UserPrincipal("427", null, null, null, null);
        String id = "90";
        var vaga = service.getJobDetails(userPrincipal, id);
        jsonParser.printValue(vaga);
    }

    @Test
    void getOnePublicDetailsWithMod() {
        userPrincipal = new UserPrincipal("427", null, null, null, null);
        var vaga = service.getPublicProfileWithModerationStatus(userPrincipal, "91");
        jsonParser.printValue(vaga);
    }

    @Test
    void getAllCreated() {
        String id = "195";
        var vagas = service.getAuthUserCreatedJobs(userPrincipal, 0, 50);
        jsonParser.printValue(vagas.getContent());
    }



//    @Test
//    void getAllReceived() {
//        String id = "274";
//        var vagas = service.getAllReceivedByUser(userPrincipal, id, new HashMap<>());
//        System.out.println(vagas.size());
//        vagas.forEach(v -> System.out.println(v.getId()));
//        jsonParser.printValue(vagas);
//    }

}
