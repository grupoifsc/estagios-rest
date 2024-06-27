package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfile;
import com.github.projetoifsc.estagios.core.dto.IIOrgPublicProfileProjectionImplProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.response.OrgPublicProfile;
import com.github.projetoifsc.estagios.app.configs.PwdEncoder;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OrgServiceUnitTest {

    // Mocking Dependency Injection
    IOrganizationUseCases organizationUseCases = Mockito.mock();
    Mapper mapper = new Mapper();
    PasswordEncoder encoder = new PwdEncoder().passwordEncoder();
    JsonParser jsonParser = new JsonParser();
    OrgService orgService = new OrgService(organizationUseCases, mapper, encoder, jsonParser);

    OrgPrivateProfileProjection dbProjection;
    OrgMocker orgMocker = new OrgMocker();
    String id;
    UserPrincipal userPrincipal;

    @BeforeEach
    void setUp() {
        id = "1";
        dbProjection = generateProjection(id);
        userPrincipal = new UserPrincipal(id, true, null, null, null);
    }


    @Test
    void whenCreateThenReturnOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, OrgEntryData.class);
        dbProjection = generateProjection(entryData);

        when(organizationUseCases.createProfile(entryData)).thenReturn(dbProjection);

        var result = orgService.create(entryData);
        assertInstanceOf(OrgPrivateProfile.class, result);
        assertEquals(entryData.getIe(), result.getIe());

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);
    }


    @Test
    void whenGetAuthUserPerfilReturnsOrgPrivateView() {

        when(organizationUseCases.getPrivateProfile(id, id))
                .thenReturn(dbProjection);

        var result = orgService.getAuthUserPerfil(userPrincipal);
        assertInstanceOf(OrgPrivateProfile.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenGetUserPublicProfileReturnOrgPublicView() {
        var org = new IIOrgPublicProfileProjectionImplProjection(id, true);

        when(organizationUseCases.getPublicProfile(id, id))
                .thenReturn(org);

        var result = orgService.getUserPublicProfile(userPrincipal, id);
        assertInstanceOf(OrgPublicProfile.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertFalse(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenUpdatePerfilReturnUpdatedOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, OrgEntryData.class);
        dbProjection = generateProjection(entryData);

        when(organizationUseCases.updateProfile(id, id, entryData))
                .thenReturn(dbProjection);

        var result = orgService.updateAuthUserPerfil(userPrincipal, entryData);
        assertInstanceOf(OrgPrivateProfile.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenDeleteUserReturnsNull() {
        doNothing().when(organizationUseCases).deleteProfile(id, id);
        assertDoesNotThrow(() -> orgService.deleteAuthUserPerfil(userPrincipal));
    }


    @Test
    void whenGetSchoolsRetunsPageWithPublicView() {

        when(organizationUseCases.getAllSchools())
                .thenReturn(new PageImpl<>(new ArrayList<>(
                        List.of(
                        new IIOrgPublicProfileProjectionImplProjection("10", true),
                        new IIOrgPublicProfileProjectionImplProjection("11", true),
                        new IIOrgPublicProfileProjectionImplProjection("12", true)
                ))));

        var result = orgService.getAllSchools(userPrincipal);

        result.getContent().forEach(org -> {
            assertInstanceOf(OrgPublicProfile.class, org);
            var jsonString = jsonParser.valueAsString(org);
            assertFalse(jsonString.contains("username"));
        });
        jsonParser.printValue(result.getContent());

    }


    private OrgPrivateProfileProjectionTestImpl generateProjection() {
        var ent = orgMocker.generateWithIdAsZero();
        return mapper.map(ent, OrgPrivateProfileProjectionTestImpl.class);
    }

    private OrgPrivateProfileProjectionTestImpl generateProjection(String id) {
        var ent = orgMocker.generateWithIdAsZero();
        return mapper.map(ent, OrgPrivateProfileProjectionTestImpl.class);
    }


    private OrgPrivateProfileProjectionTestImpl generateProjection(OrgEntryData entryData) {
        return mapper.map(entryData, OrgPrivateProfileProjectionTestImpl.class);
    }


}
