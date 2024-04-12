package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
    Organization save(Organization org);
    Optional<Organization> findById(long id);
}
