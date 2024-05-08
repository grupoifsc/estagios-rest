package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface JobRepository extends ListPagingAndSortingRepository<JobEntity, Long> {
    JobEntity save(JobEntity jobEntity);
    Optional<JobEntity> findById(long id);
}
