package com.web.logincrud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.logincrud.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	Optional<UserModel> findByEmail(String email);
	boolean existsByEmail(String email);
}
