package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    Optional<UserCredentialsEntity> findByEmail(String email);

    <T> List<T> findAllProjectedBy(Class<T> type);
    <T> T findFirstProjectedBy(Class<T> type);


}
