package com.kitm.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitm.movies.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
}
