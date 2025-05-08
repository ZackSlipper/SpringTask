package com.kitm.movies.service;

import java.util.List;

import com.kitm.movies.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findById(Long id);

	Category save(Category category);

	void deleteById(Long id);
}
