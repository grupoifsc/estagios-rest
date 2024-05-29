package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {
}
