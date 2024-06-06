package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicOrgProfileResponse;
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
    OrgService orgService = new OrgService(organizationUseCases, mapper, encoder);

    JsonParser jsonParser = new JsonParser();
    OrgPrivateProfileProjection dbProjection;
    OrgMocker orgMocker = new OrgMocker();
    String id;
    UserPrincipal userPrincipal;

    @BeforeEach
    void setUp() {
        id = "1";
        dbProjection = generateProjection();
        userPrincipal = new UserPrincipal(id, null, null, null);
    }


    @Test
    void whenCreateThenReturnOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, NewOrgProfileRequest.class);
        dbProjection = generateProjection(entryData);

        when(organizationUseCases.createProfile(entryData)).thenReturn(dbProjection);

        var result = orgService.create(entryData);
        assertInstanceOf(PrivateOrgProfileResponse.class, result);
        assertEquals(entryData.getIe(), result.getIe());

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);
    }


    @Test
    void whenGetAuthUserPerfilReturnsOrgPrivateView() {
        dbProjection.setId(id);

        when(organizationUseCases.getPrivateProfile(id, id))
                .thenReturn(dbProjection);

        var result = orgService.getAuthUserPerfil(userPrincipal, id);
        assertInstanceOf(PrivateOrgProfileResponse.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenGetUserPublicProfileReturnOrgPublicView() {
        dbProjection.setId(id);

        when(organizationUseCases.getPublicProfile(id, id))
                .thenReturn(dbProjection);

        var result = orgService.getUserPublicProfile(userPrincipal, id);
        assertInstanceOf(PublicOrgProfileResponse.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertFalse(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenUpdatePerfilReturnUpdatedOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, NewOrgProfileRequest.class);
        dbProjection = generateProjection(entryData);
        dbProjection.setId(id);

        when(organizationUseCases.updateProfile(id, id, entryData))
                .thenReturn(dbProjection);

        var result = orgService.updateAuthUserPerfil(userPrincipal, id, entryData);
        assertInstanceOf(PrivateOrgProfileResponse.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenDeleteUserReturnsNull() {
        doNothing().when(organizationUseCases).deleteProfile(id, id);
        assertDoesNotThrow(() -> orgService.deleteAuthUserPerfil(userPrincipal, id));
    }


    @Test
    void whenGetSchoolsRetunsPageWithPublicView() {

        when(organizationUseCases.getSchools())
                .thenReturn(new PageImpl<>(new ArrayList<>(
                        List.of(
                        generateProjection(), generateProjection(), generateProjection()
                ))));

        var result = orgService.getAllSchools(userPrincipal);
        assertInstanceOf(PageImpl.class, result);

        result.getContent().forEach(org -> {
            assertInstanceOf(PublicOrgProfileResponse.class, org);
            var jsonString = jsonParser.valueAsString(org);
            assertFalse(jsonString.contains("username"));
        });
        jsonParser.printValue(result.getContent());

    }


    private OrgPrivateProfileTestImpl generateProjection() {
        var ent = orgMocker.generateWithIdAsZero();
        return mapper.map(ent, OrgPrivateProfileTestImpl.class);
    }


    private OrgPrivateProfileTestImpl generateProjection(NewOrgProfileRequest entryData) {
        return mapper.map(entryData, OrgPrivateProfileTestImpl.class);
    }


}
