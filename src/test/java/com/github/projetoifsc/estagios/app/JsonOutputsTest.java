package com.github.projetoifsc.estagios.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.projetoifsc.estagios.app.model.request.JobEntryData;
import com.github.projetoifsc.estagios.app.model.response.JobPrivateDetails;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;

class JsonOutputsTest {

    private final JsonParser parser = new JsonParser();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void name() {
        System.out.println("Vaga Private");
        parser.printValue(new JobPrivateDetails());

        System.out.println("New Vaga");
        parser.printValue(new JobEntryData());

    }


}
