package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    Optional<AddressMainEntity> findFirstAddressMainByOwnerId(long id);

    <T> List<T> findByOwnerId(long ownerId, Class<T> type);

    void deleteAllByOwner(OrganizationEntity owner);

}
