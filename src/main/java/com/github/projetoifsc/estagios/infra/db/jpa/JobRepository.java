package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface JobRepository extends ListPagingAndSortingRepository<Job, Long> {
    Job save(Job job);
    Optional<Job> findById(long id);
}
