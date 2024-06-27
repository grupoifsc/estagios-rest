package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface ContactRepository extends CrudRepository<ContactEntity, Long> {

    <T> List<T> findByOwnerId(long ownerId, Class<T> type);
    Optional<ContactMainEntity> findFirstContactMainByOwner(OrgEntity owner);
    Optional<ContactApplianceEntity> findFirstContactApplianceByOwner(OrgEntity owner);

    void deleteAllByOwner(OrgEntity owner);

    <T> List<T> findAllProjectedBy(Class<T> type);
    <T> T findFirstProjectedBy(Class<T> type);


}