package com.kitm.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitm.movies.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
