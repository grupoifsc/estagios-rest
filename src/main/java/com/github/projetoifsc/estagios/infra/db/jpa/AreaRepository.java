package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AreaRepository extends ListCrudRepository<AreaEntity, Long> {

    List<AreaEntity> findByIdIn(List<Long> ids);

    <T> List<T> findAllProjectedBy(Class<T> type);
    <T> T findFirstProjectedBy(Class<T> type);

}
