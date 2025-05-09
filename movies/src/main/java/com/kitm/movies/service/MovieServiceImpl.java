package com.kitm.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitm.movies.dao.CategoryRepository;
import com.kitm.movies.dao.MovieRepository;
import com.kitm.movies.entity.Category;
import com.kitm.movies.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
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

	@Override
	public List<Movie> findAllByCategoryId(Long categoryId) {
		return movieRepository.findAllByCategoryId(categoryId);
	}

	@Override
	public List<Movie> findAllByCategoryName(String categoryName) {
		Optional<Category> category = categoryRepository.findByName(categoryName);
		if (category.isEmpty()) {
			throw new RuntimeException("Did not find category name - " + categoryName);
		}

		Category categoryEntity = category.get();
		return movieRepository.findAllByCategoryId(categoryEntity.getId());
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
}
