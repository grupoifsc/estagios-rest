package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.controller.vagas.AuthUserVagasController;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VagaControllerTest {

    @Autowired
    AuthUserVagasController authUserVagasController;

    @Autowired
    JsonParser jsonParser;

    UserPrincipal userPrincipal;

    @Test
    void getAllCreatedByWithPagination() {
        userPrincipal = new UserPrincipal("195", null, null, null, null);
        var vagas = authUserVagasController.getAllCreatedByUser(userPrincipal, 5, 0);
        jsonParser.printValue(vagas);
    }
}
