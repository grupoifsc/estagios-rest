package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface OrganizationRepository extends PagingAndSortingRepository<OrganizationEntity, Long> {

    OrganizationEntity save(OrganizationEntity org);

    Optional<OrganizationEntity> findById(long id);
    <T> Optional<T> findById(long id, Class<T> type);

    <T> Optional<T> findByUsername(String username, Class<T> type);

    <T> List<T> findByIdIn(List<Long> ids, Class<T> type);

    void delete(OrganizationEntity entity);

    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> type);

    <T> Page<T> findByIe(boolean ie, Pageable pageable, Class<T> type);

}
