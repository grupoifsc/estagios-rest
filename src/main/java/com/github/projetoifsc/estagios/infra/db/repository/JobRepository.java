package com.github.projetoifsc.estagios.infra.db.repository;

import com.github.projetoifsc.estagios.infra.db.model.Job;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends ListPagingAndSortingRepository<Job, Long> {

    Job save(Job job);
    Optional<Job> findById(long id);
}
