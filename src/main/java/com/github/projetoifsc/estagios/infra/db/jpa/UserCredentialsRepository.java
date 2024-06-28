package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    @Query(value =
        "SELECT u FROM UserCredentialsEntity u " +
            "LEFT JOIN u.organization as org " +
        "WHERE u.email = :email ")
    Optional<UserCredentialsEntity> findByEmail(String email);

    @Query(value =
        "SELECT uc.id FROM UserCredentialsEntity uc " +
        "WHERE uc.organization.id = :orgId "
    )
    Optional<Long> selectIdByOrganizationId (long orgId);

}
