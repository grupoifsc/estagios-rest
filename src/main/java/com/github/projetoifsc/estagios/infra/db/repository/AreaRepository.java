package com.github.projetoifsc.estagios.infra.db.repository;

import com.github.projetoifsc.estagios.infra.db.model.Area;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends ListPagingAndSortingRepository<Area, Long> {

    Optional<Area> findAreaById(Long id);

    Area save(Area area);
}

// TODO Decidir como resolver os Optional
