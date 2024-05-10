package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AreaRepository extends ListCrudRepository<AreaEntity, Long> {
}
