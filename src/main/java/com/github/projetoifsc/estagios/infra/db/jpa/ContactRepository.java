package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface ContactRepository extends CrudRepository<ContactEntity, Long> {

    List<ContactEntity> findByOwner(OrganizationEntity owner);
    Optional<ContactMainEntity> findFirstContactMainByOwner(OrganizationEntity owner);
    Optional<ContactApplianceEntity> findFirstContactApplianceByOwner(OrganizationEntity owner);

    void deleteAllByOwner(OrganizationEntity owner);

}