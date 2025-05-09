package com.kitm.movies.service;

import java.util.List;

import com.kitm.movies.entity.Movie;

public interface MovieService {
	List<Movie> findAll();

	List<Movie> findAllByCategoryId(Long categoryId);

	List<Movie> findAllByCategoryName(String categoryName);

	List<Movie> findByTitle(String title);

	Movie findById(Long id);

	Movie save(Movie movie);

	void deleteById(Long id);
}
