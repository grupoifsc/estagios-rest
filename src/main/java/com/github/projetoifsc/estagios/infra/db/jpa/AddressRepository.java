package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    Optional<AddressMainEntity> findFirstByOwnerId(long id);

    Optional<AddressMainEntity> findFirstAddressMainByOwner(OrganizationEntity owner);

    List<AddressEntity> findByOwner(OrganizationEntity owner);

    void deleteAllByOwner(OrganizationEntity owner);

}
