package com.kitm.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitm.movies.dao.MovieRepository;
import com.kitm.movies.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findById(Long id) {
		Optional<Movie> result = movieRepository.findById(id);
		Movie movie = null;
		if (result.isPresent()) {
			movie = result.get();
		} else {
			throw new RuntimeException("Did not find movie id - " + id);
		}
		return movie;
	}

	@Override
	public Movie save(Movie movie){
		return movieRepository.save(movie);
	}

	@Override
	public void deleteById(Long id) {
		movieRepository.deleteById(id);
	}
}
