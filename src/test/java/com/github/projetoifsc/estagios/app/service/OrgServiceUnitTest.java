package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.interfaces.OrgPrivateProfileProjection;
import com.github.projetoifsc.estagios.app.view.OrgPrivateProfileBasicView;
import com.github.projetoifsc.estagios.app.view.OrgPublicProfileBasicView;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.infra.db.jpa.OrgMocker;
import com.github.projetoifsc.estagios.utils.JsonParser;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OrgServiceUnitTest {

    // Mocking Dependency Injection
    IOrganizationUseCases organizationUseCases = Mockito.mock();
    Mapper mapper = new Mapper();
    OrgService orgService = new OrgService(organizationUseCases, mapper);

    JsonParser jsonParser = new JsonParser();
    OrgPrivateProfileProjection dbProjection;
    OrgMocker orgMocker = new OrgMocker();
    String id;


    @BeforeEach
    void setUp() {
        id = "1";
        dbProjection = generateProjection();
    }


    @Test
    void whenCreateThenReturnOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, OrgPrivateProfileBasicView.class);
        dbProjection = generateProjection(entryData);

        when(organizationUseCases.createProfile(entryData)).thenReturn(dbProjection);

        var result = orgService.create(entryData);
        assertInstanceOf(OrgPrivateProfileBasicView.class, result);
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

        var result = orgService.getAuthUserPerfil(id);
        assertInstanceOf(OrgPrivateProfileBasicView.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenGetUserPublicProfileReturnOrgPublicView() {
        dbProjection.setId(id);

        when(organizationUseCases.getPublicProfile(id, id))
                .thenReturn(dbProjection);

        var result = orgService.getUserPublicProfile(id);
        assertInstanceOf(OrgPublicProfileBasicView.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertFalse(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenUpdatePerfilReturnUpdatedOrgPrivateView() {
        var ent = orgMocker.generateWithIdAsZero();
        var entryData = mapper.map(ent, OrgPrivateProfileBasicView.class);
        dbProjection = generateProjection(entryData);
        dbProjection.setId(id);

        when(organizationUseCases.updateProfile(id, id, entryData))
                .thenReturn(dbProjection);

        var result = orgService.updateAuthUserPerfil(id, entryData);
        assertInstanceOf(OrgPrivateProfileBasicView.class, result);

        var jsonString = jsonParser.valueAsString(result);
        assertTrue(jsonString.contains("username"));
        System.out.println(jsonString);

    }


    @Test
    void whenDeleteUserReturnsNull() {
        doNothing().when(organizationUseCases).deleteProfile(id, id);
        assertDoesNotThrow(() -> orgService.deleteAuthUserPerfil(id));
    }


    @Test
    void whenGetSchoolsRetunsPageWithPublicView() {

        when(organizationUseCases.getSchools())
                .thenReturn(new PageImpl<>(new ArrayList<>(
                        List.of(
                        generateProjection(), generateProjection(), generateProjection()
                ))));

        var result = orgService.getAllSchools();
        assertInstanceOf(PageImpl.class, result);

        result.getContent().forEach(org -> {
            assertInstanceOf(OrgPublicProfileBasicView.class, org);
            var jsonString = jsonParser.valueAsString(org);
            assertFalse(jsonString.contains("username"));
        });
        jsonParser.printValue(result.getContent());

    }


    private OrgPrivateProfileTestImpl generateProjection() {
        var ent = orgMocker.generateWithIdAsZero();
        return mapper.map(ent, OrgPrivateProfileTestImpl.class);
    }


    private OrgPrivateProfileTestImpl generateProjection(OrgPrivateProfileBasicView entryData) {
        return mapper.map(entryData, OrgPrivateProfileTestImpl.class);
    }


}
