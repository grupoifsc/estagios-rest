package com.github.projetoifsc.estagios.infra.db.repository;

import com.github.projetoifsc.estagios.infra.db.model.Organization;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
    Organization save(Organization org);
    Organization findById(long id);
}
