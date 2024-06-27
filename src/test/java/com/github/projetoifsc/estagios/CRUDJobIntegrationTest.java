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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CRUDJobIntegrationTest {

    private final VagaService jobService;
    private final JsonParser jsonParser;
    private final OrgService orgService;
    private final AuthenticationService authenticationService;
    private final AuthUserService authUserService;

    @Autowired
    CRUDJobIntegrationTest(VagaService jobService, JsonParser jsonParser, OrgService orgService, AuthenticationService authenticationService, AuthUserService authUserService) {
        this.jobService = jobService;
        this.jsonParser = jsonParser;
        this.orgService = orgService;
        this.authenticationService = authenticationService;
        this.authUserService = authUserService;
    }

    @Test
    void createJob() {
        var user = authUserService.findByUsername("joana@email.com").orElseThrow();
        var userPrincipal = new UserPrincipal(user.getId(), user.getIe(), user.getUsername(), user.getPassword(), user.getAuthorities());

        var job = new JobEntryData();
        job.setAreasIds(List.of("1"));
        job.setLevel(ELevel.GRADUACAO);
        job.setFormat(EFormat.HIBRIDO);
        job.setPeriod(EPeriod.NOTURNO);

        var created = jobService.create(userPrincipal, job);
        jsonParser.printValue(created);

    }

    @Test
    void updateJob() {
        var user = authUserService.findByUsername("joana@email.com").orElseThrow();
        var userPrincipal = new UserPrincipal(user.getId(), user.getIe(), user.getUsername(), user.getPassword(), user.getAuthorities());

        var job = new JobEntryData();
        job.setAreasIds(List.of("1"));
        job.setLevel(ELevel.MEDIO);
        job.setFormat(EFormat.REMOTO);
        job.setPeriod(EPeriod.VESPERTINO);

        var created = jobService.update(userPrincipal, "61", job);
        jsonParser.printValue(created);

    }

    @Test
    void getDisponiveis() {
        var user = authUserService.findByUsername("joana@email.com").orElseThrow();
        var userPrincipal = new UserPrincipal(user.getId(), user.getIe(), user.getUsername(), user.getPassword(), user.getAuthorities());

        var vagas = jobService.getAuthUserAvailableJobs(userPrincipal, 0, 10);
        jsonParser.printValue(vagas);
        jsonParser.printValue(vagas.getContent());
    }
}
