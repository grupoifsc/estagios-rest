package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.app.model.request.EFormat;
import com.github.projetoifsc.estagios.app.model.request.ELevel;
import com.github.projetoifsc.estagios.app.model.request.EPeriod;
import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthUserService;
import com.github.projetoifsc.estagios.app.service.AuthenticationService;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.IJobDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CRUDJobIntegrationTest {

    private final VagaService jobService;
    private final JsonParser jsonParser;
    private final AuthUserService authUserService;

    private UserPrincipal userPrincipal;
    private Object value;

    @Autowired
    CRUDJobIntegrationTest(VagaService jobService, JsonParser jsonParser, AuthUserService authUserService) {
        this.jobService = jobService;
        this.jsonParser = jsonParser;
        this.authUserService = authUserService;
    }

    @BeforeEach
    void setUp() {
        var user = authUserService.findByUsername("ifsc@email.com").orElseThrow();
        userPrincipal = new UserPrincipal(user.getId(), user.getIe(), user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @AfterEach
    void tearDown() {
        jsonParser.printValue(value);
    }

    @Test
    void createJob() {
        var job = new JobEntryData();
        job.setAreasIds(List.of("1"));
        job.setLevel(ELevel.GRADUACAO);
        job.setFormat(EFormat.HIBRIDO);
        job.setPeriod(EPeriod.NOTURNO);

        value = jobService.create(userPrincipal, job);
    }


    @Test
    void updateJob() {
        var job = new JobEntryData();
        job.setAreasIds(List.of("2"));
        job.setLevel(ELevel.MEDIO);
        job.setFormat(EFormat.REMOTO);
        job.setPeriod(EPeriod.VESPERTINO);

        value = jobService.update(userPrincipal, "74", job);
    }




    @Test
    void getOnePrivateDetails() {
        value = jobService.getPrivateProfile(userPrincipal, "90");
    }


    @Test
    void getOnePublicDetails() {
        value = jobService.getPublicProfile(userPrincipal, "16");
    }



    @Test
    void getAvailable() {
        value = jobService.getAuthUserAvailableJobs(userPrincipal, 0, 10);
    }


    @Test
    void getCreated() {
        value = jobService.getAuthUserCreatedJobs(userPrincipal, 0, 10);
    }

    @Test
    void getRejected() {
        value= jobService.getAuthUserRejectedJobs(userPrincipal, 0, 10);
    }

    @Test
    void getPending() {
        value = jobService.getAuthUserPendingJobs(userPrincipal,0, 10).getContent();
    }

    @Test
    void delete() {
        var id = "64";
        jobService.delete(userPrincipal, id);
    }

}
