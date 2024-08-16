package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.core.IJobUseCases;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonParseException;

@SpringBootTest
class JobReadIntegrationTest {

    private final IJobUseCases useCases;
    private final JsonParser jsonParser;

    @Autowired
    JobReadIntegrationTest(IJobUseCases useCases, JsonParser jsonParser) {
        this.useCases = useCases;
        this.jsonParser = jsonParser;
    }

    @Test
    void getOnePublicDetails() {
        var job = useCases.getOnePublicDetails("427", "90");
        jsonParser.printValue(job);
    }

}
