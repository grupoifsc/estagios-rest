package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface ContactRepository extends CrudRepository<ContactEntity, Long> {

    <T> List<T> findByOwnerId(long ownerId, Class<T> type);
    Optional<ContactMainEntity> findFirstContactMainByOwner(OrganizationEntity owner);
    Optional<ContactApplianceEntity> findFirstContactApplianceByOwner(OrganizationEntity owner);

    void deleteAllByOwner(OrganizationEntity owner);

}