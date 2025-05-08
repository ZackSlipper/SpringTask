package com.kitm.movies.mapper;

import java.time.LocalDateTime;

import com.kitm.movies.dto.MovieCreateDTO;
import com.kitm.movies.dto.MovieResponseDTO;
import com.kitm.movies.entity.Movie;

public class MovieMapper {
	public static Movie toEntity(MovieCreateDTO dto) {
		Movie movie = new Movie();

		movie.setTitle(dto.getTitle());
		movie.setDescription(dto.getDescription());
		movie.setThumbnail(dto.getThumbnail());
		movie.setCategoryId(dto.getCategoryId());
		movie.setReleaseYear(dto.getReleaseYear());
		movie.setImdbRating(dto.getImdbRating());
		movie.setCreatedAt(LocalDateTime.now());
		movie.setUpdatedAt(LocalDateTime.now());

		return movie;
	}
	
	public static MovieResponseDTO toResponse(Movie movie) {
		MovieResponseDTO dto = new MovieResponseDTO();
		
		dto.setId(movie.getId());
		dto.setTitle(movie.getTitle());
		dto.setDescription(movie.getDescription());
		dto.setThumbnail(movie.getThumbnail());
		dto.setCategoryId(movie.getCategoryId());
		dto.setReleaseYear(movie.getReleaseYear());
		dto.setImdbRating(movie.getImdbRating());
		dto.setCreatedAt(movie.getCreatedAt());
		dto.setUpdatedAt(movie.getUpdatedAt());

		return dto;
	}
}
