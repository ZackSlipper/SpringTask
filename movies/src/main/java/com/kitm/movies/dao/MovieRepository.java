package com.kitm.movies.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitm.movies.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findAllByCategoryId(Long categoryId);
	List<Movie> findByTitle(String title);
}
