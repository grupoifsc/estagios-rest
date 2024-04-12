package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AreaRepository extends ListPagingAndSortingRepository<Area, Long> {

    Optional<Area> findAreaById(Long id);
    Area save(Area area);

}
