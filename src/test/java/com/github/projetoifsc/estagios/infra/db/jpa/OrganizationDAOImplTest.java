package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.request.NewOrgProfileRequest;
import com.github.projetoifsc.estagios.app.model.response.PrivateOrgProfileResponse;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


@SpringBootTest
class OrganizationDAOImplTest {

    OrgMocker orgMocker = new OrgMocker(new Faker(new Locale("pt-BR")), new GeradorCnpj());

    @Autowired
    OrganizationRepository repository;

    @Autowired
    OrganizationDAOImpl organizationDB;

    @Autowired
    JobDAOImpl jobDB;

    @Autowired
    Mapper mapper;

    @Autowired
    JsonParser jsonParser;


    @Autowired
    private OrganizationRepository organizationRepository;


    IOrganization org;

    @BeforeEach
    void setUp() {
        org = organizationRepository.save(orgMocker.generate());
    }

    @Test
    void findByIdReturnsBasicInfoOrNull() {

        var dto = organizationDB.findById(org.getId());

        jsonParser.printValue(dto);

    }

    @Test
    void findAllByIdReturnsBasicInfoOrEmptyList() {
        var ids = List.of(org.getId());
        var dtos = organizationDB.findAllById(ids);

        jsonParser.printValue(dtos);

    }

    @Test
    void findByUsernameReturnsBasicInfoOrNull() {
        var ent = mapper.map(org, PrivateOrgProfileResponse.class);
        var dto = organizationDB.findByUsername(ent.getUsername());

        jsonParser.printValue(dto);

    }

    @Test
    void saveReturnsPrivateProfile() {
        var entity = new OrgMocker().generateWithIdAsZero();
        var newUser = mapper.map(entity, NewOrgProfileRequest.class);
        newUser.setEmail("teste@teste.com");
        newUser.setPassword("minhaSenha");
        jsonParser.printValue(newUser);
        var saved = organizationDB.save(newUser);

        jsonParser.printValue(saved);

    }

    @Test
    void delete() {
        jsonParser.printValue(org);
        organizationDB.delete(org.getId());
    }


    @Test
    void getPublicProfile() {

        var dto = organizationDB.getOnePublicProfile(org.getId());
        jsonParser.printValue(dto);

    }


    @Test
    void getOnePrivateProfile() {

        var dto = organizationDB.getOnePrivateProfile(org.getId());

        jsonParser.printValue(dto);

    }

    @Test
    void getAllCreatedJobsSummaryFromOrgIsPaginated() {

    }

    @Test
    void getExclusiveReceivedJobsSummaryForOrgIsPaginated() {

    }


    @Test
    void getAllSchoolsPublicProfileIsPaginated() {

        var dtos = organizationDB.getAllSchoolsPublicProfile();

        jsonParser.printValue(dtos);
    }


    @Test
    void getMainAddressByOwnerId() {
        String id = "195";
        var mainAddr = organizationDB.getMainAddress(id);
        jsonParser.printValue(mainAddr);
    }

    @Test
    void getAllExclusiveReceiversForJob() {
        String id = "8";
        var receivers = organizationDB.getExclusiveReceiversForJob(id);
        System.out.println(receivers.size());
        System.out.println(jsonParser.valueAsString(receivers));
    }

    @Test
    void getAllAddresses() {
        String id = "197";
        var addresses = organizationDB.getAllAddresses(id);
        System.out.println(addresses.size());
        System.out.println(jsonParser.valueAsString(addresses));
    }

    @Test
    void getAllContacts() {
        String id = "197";
        var contacts = organizationDB.getAllContacts(id);
        System.out.println(contacts.size());
        System.out.println(jsonParser.valueAsString(contacts));
    }

}
