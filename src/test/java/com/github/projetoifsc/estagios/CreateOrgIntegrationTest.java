package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.request.UserCredentialsProjectionEntryData;
import com.github.projetoifsc.estagios.app.model.response.Address;
import com.github.projetoifsc.estagios.app.model.response.Contact;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreateOrgIntegrationTest {

    private final OrgService orgService;
    private final JsonParser jsonParser;

    @Autowired
    CreateOrgIntegrationTest(OrgService orgService, JsonParser jsonParser) {
        this.orgService = orgService;
        this.jsonParser = jsonParser;
    }


    @Test
    void createOrg() {
        var entryOrg = new OrgEntryData();
        entryOrg.setNome("Ju");
        entryOrg.setUserCredentials(new UserCredentialsProjectionEntryData());
        entryOrg.getUserCredentials().setEmail("joana@email.com");
        entryOrg.getUserCredentials().setSenha("banana");
        entryOrg.setMainAddress(new Address());
        entryOrg.setApplianceContact(new Contact());
        entryOrg.setMainContact(new Contact());
        entryOrg.setIe(true);

        var created = orgService.create(entryOrg);
        jsonParser.printValue(created);
    }



}
