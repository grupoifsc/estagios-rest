package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.request.UserCredentialsProjectionEntryData;
import com.github.projetoifsc.estagios.app.model.response.Address;
import com.github.projetoifsc.estagios.app.model.response.Contact;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.app.service.AuthUserService;
import com.github.projetoifsc.estagios.app.service.OrgService;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CRUDOrgIntegrationTest {

    private final OrgService orgService;
    private final JsonParser jsonParser;
    private final AuthUserService authUserService;

    @Autowired
    CRUDOrgIntegrationTest(OrgService orgService, JsonParser jsonParser, AuthUserService authUserService) {
        this.orgService = orgService;
        this.jsonParser = jsonParser;
        this.authUserService = authUserService;
    }

    private UserPrincipal userPrincipal;
    private Object value;

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
    void createOrg() {
        var entryOrg = new OrgEntryData();
        entryOrg.setNome("JuJu");
        entryOrg.setUserCredentials(new UserCredentialsProjectionEntryData());
        entryOrg.getUserCredentials().setEmail("joana2@email.com");
        entryOrg.getUserCredentials().setSenha("banana");
        entryOrg.setMainAddress(new Address());
        entryOrg.setApplianceContact(new Contact());
        entryOrg.setMainContact(new Contact());
        entryOrg.setIe(true);

        value = orgService.create(entryOrg);

    }


    @Test
    void updateOrganization() {
        var entryOrg = new OrgEntryData();
        entryOrg.setNome("Jurubebis");
        entryOrg.setUserCredentials(new UserCredentialsProjectionEntryData());
        entryOrg.getUserCredentials().setEmail("joana3@email.com");
        entryOrg.getUserCredentials().setSenha("banana");
        entryOrg.setMainAddress(new Address());
        entryOrg.setApplianceContact(new Contact());
        entryOrg.setMainContact(new Contact());
        entryOrg.setIe(true);
        value = orgService.updateAuthUserPerfil(userPrincipal, entryOrg);
    }

//    @Test
//    void deleteOrganization() {
//        orgService.deleteAuthUserPerfil(userPrincipal);
//    }

    @Test
    void getAuthUserPrivateProfile() {
        value = orgService.getAuthUserPerfil(userPrincipal);
    }

    @Test
    void getOrgPublicProfile() {
        value = orgService.getUserPublicProfile(userPrincipal, "195");
    }

    @Test
    void getAllSchools() {
        value = orgService.getAllSchools(userPrincipal);
    }

    @Test
    void getAllAuthUserAddresses() {
        value = orgService.getAuthUserAddresses(userPrincipal);
    }

    @Test
    void getAllAuthUserContacts() {
        value = orgService.getAuthUserContacts(userPrincipal);
    }

}
