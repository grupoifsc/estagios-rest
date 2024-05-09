package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<AddressMain> findFirstAddressMainByOwner(OrganizationEntity owner);

    List<Address> findByOwner(OrganizationEntity owner);

    void deleteAllByOwner(OrganizationEntity owner);

}
