package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByOwner(OrganizationEntity owner);
    Optional<ContactMain> findFirstContactMainByOwner(OrganizationEntity owner);
    Optional<ContactAppliance> findFirstContactApplianceByOwner(OrganizationEntity owner);

    void deleteAllByOwner(OrganizationEntity owner);

}