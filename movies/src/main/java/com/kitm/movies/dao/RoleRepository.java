package com.kitm.movies.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitm.movies.entity.Role;
import com.kitm.movies.enums.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(Roles name);
}