package com.kitm.movies.service;

import java.util.List;

import com.kitm.movies.entity.Movie;

public interface MovieService {
	List<Movie> findAll();

	Movie findById(Long id);

	Movie save(Movie movie);

	void deleteById(Long id);
}
