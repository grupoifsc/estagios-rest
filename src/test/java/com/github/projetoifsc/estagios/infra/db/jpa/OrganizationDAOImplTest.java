package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.app.model.request.OrgEntryData;
import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfile;
import com.github.projetoifsc.estagios.core.models.IOrg;
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


    IOrg org;

    @BeforeEach
    void setUp() {
        org = organizationRepository.save(orgMocker.generate());
    }

    @Test
    void findByIdSummaryProjectionReturnsBasicInfoOrNull() {

        var dto = organizationDB.findByIdSummaryProjection(org.getId());

        jsonParser.printValue(dto);

    }

    @Test
    void findAllByIdSummaryProjectionReturnsBasicInfoOrEmptyList() {
        var ids = List.of(org.getId());
        var dtos = organizationDB.findAllByIdSummaryProjection(ids);

        jsonParser.printValue(dtos);

    }

//    @Test
//    void findByUsernameWithCredentialsReturnsBasicInfoOrNull() {
//        var ent = mapper.map(org, OrgPrivateProfile.class);
//        var dto = organizationDB.findByUsernameWithCredentials(ent.getUserCredentials().getEmail());
//
//        jsonParser.printValue(dto);
//
//    }

    @Test
    void saveReturnsPrivateProfile() {
        var entity = new OrgMocker().generateWithIdAsZero();
        var newUser = mapper.map(entity, OrgEntryData.class);

        //newUser.setEmail("teste@teste.com");
        //newUser.setPassword("minhaSenha");
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

        var dto = organizationDB.getOrgPublicProfile(org.getId());
        jsonParser.printValue(dto);

    }


    @Test
    void getOrgPrivateProfile() {

        var dto = organizationDB.getOrgPrivateProfile(org.getId());

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
        var mainAddr = organizationDB.getOrgMainAddress(id);
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
        String id = "397";
        var addresses = organizationDB.getAllAddressesFromOrg(id);
        System.out.println(addresses.size());
        System.out.println(jsonParser.valueAsString(addresses));
    }

    @Test
    void getAllContactsFromOrg() {
        String id = "397";
        var contacts = organizationDB.getAllContactsFromOrg(id);
        System.out.println(contacts.size());
        System.out.println(jsonParser.valueAsString(contacts));
    }

}
