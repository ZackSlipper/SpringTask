package com.kitm.movies.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitm.movies.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}
