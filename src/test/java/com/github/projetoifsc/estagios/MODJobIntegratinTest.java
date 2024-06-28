package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthUserService;
import com.github.projetoifsc.estagios.app.service.VagaService;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MODJobIntegratinTest {

    private final JsonParser jsonParser;
    private final AuthUserService authUserService;
    private final VagaService vagaService;

    private UserPrincipal userPrincipal;
    private Object value;

    @Autowired
    MODJobIntegratinTest(JsonParser jsonParser, AuthUserService authUserService, VagaService vagaService) {
        this.jsonParser = jsonParser;
        this.authUserService = authUserService;
        this.vagaService = vagaService;
    }


    @BeforeEach
    void setUp() {
        var user = authUserService.findByUsername("joana3@email.com").orElseThrow();
        userPrincipal = new UserPrincipal(user.getId(), user.getIe(), user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @AfterEach
    void tearDown() {
        jsonParser.printValue(value);
    }


    @Test
    void approve() {
        vagaService.approve(userPrincipal, List.of("62"));
    }

    @Test
    void reject() {
        vagaService.reject(userPrincipal, List.of("50"));
    }

}
