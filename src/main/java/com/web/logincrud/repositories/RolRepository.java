package com.web.logincrud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.logincrud.model.RolModel;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer> {
    Optional<RolModel> findByDescription(String description);
    boolean existsByDescription(String description);
}
