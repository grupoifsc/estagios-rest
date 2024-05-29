package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    Optional<UserCredentialsEntity> findByEmail(String email);

}
