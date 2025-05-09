package com.kitm.movies.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kitm.movies.dto.MovieCreateDTO;
import com.kitm.movies.dto.MovieResponseDTO;
import com.kitm.movies.dto.search.MovieCategoryIdDTO;
import com.kitm.movies.dto.search.MovieCategoryNameDTO;
import com.kitm.movies.dto.search.MovieTitleDTO;
import com.kitm.movies.entity.Movie;
import com.kitm.movies.mapper.MovieMapper;
import com.kitm.movies.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	private MovieService movieService;
	private ObjectMapper objectMapper;

	@Autowired
	public MovieController(MovieService movieService, ObjectMapper objectMapper) {
		this.movieService = movieService;
		this.objectMapper = objectMapper;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
	public ResponseEntity<Map<String, Object>> findAll() {
		List<MovieResponseDTO> movies = movieService.findAll()
			.stream()
			.map(MovieMapper::toResponse)
			.collect(Collectors.toList());
			
		
		Map<String, Object> response = Map.of(
			"status", "Success",
			"timestamp", System.currentTimeMillis(),
			"data", movies
		);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id) {
		Optional<Movie> results = Optional.ofNullable(movieService.findById(id));
		return results.map(movie -> ResponseEntity.ok(MovieMapper.toResponse(movie)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/search/title")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findByTitle(@Valid @RequestBody MovieTitleDTO movieTitleDTO) {
		List<Movie> results = movieService.findByTitle(movieTitleDTO.getTitle());
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Map.of("movies", results));
	}

	@PostMapping("/search/categoryName")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findByCategoryName(@Valid @RequestBody MovieCategoryNameDTO movieCategoryNameDTO) {
		List<Movie> results = movieService.findAllByCategoryName(movieCategoryNameDTO.getName());
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Map.of("movies", results));
	}

	@PostMapping("/search/categoryId")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findByCategoryId(@Valid @RequestBody MovieCategoryIdDTO movieCategoryIdDTO) {
		List<Movie> results = movieService.findAllByCategoryId(movieCategoryIdDTO.getId());
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Map.of("movies", results));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<MovieResponseDTO> addMovie(@Valid @RequestBody MovieCreateDTO movieDTO) {
		Movie saved = movieService.save(MovieMapper.toEntity(movieDTO));
		return ResponseEntity.ok(MovieMapper.toResponse(saved));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			return ResponseEntity.notFound().build();
		}

		movieService.deleteById(id);
		return ResponseEntity.ok("Deleted movie id - " + id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody MovieCreateDTO movieDTO) {
		Movie existingMovie = movieService.findById(id);
		if (existingMovie == null) {
			return ResponseEntity.notFound().build();
		}

		Movie updatedMovie = MovieMapper.toEntity(movieDTO);
		updatedMovie.setId(id);
		updatedMovie.setCreatedAt(existingMovie.getCreatedAt());
		updatedMovie.setUpdatedAt(LocalDateTime.now());

		Movie movie = movieService.save(updatedMovie);

		return ResponseEntity.ok(MovieMapper.toResponse(movie));
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<MovieResponseDTO> patchMovie(@PathVariable Long id, @RequestBody Map<String, Object> pathPayload) {
		Movie existingMovie = movieService.findById(id);
		if (existingMovie == null) {
			return ResponseEntity.notFound().build();
		}

		if (pathPayload == null || pathPayload.isEmpty() || pathPayload.containsKey("id")) {
			return ResponseEntity.badRequest().body(null);
		}

		ObjectNode movieNode = objectMapper.convertValue(existingMovie, ObjectNode.class);
		ObjectNode pathNode = objectMapper.convertValue(pathPayload, ObjectNode.class);
		movieNode.setAll(pathNode);

		Movie patchedMovie = objectMapper.convertValue(movieNode, Movie.class);
		Movie savedMovie = movieService.save(patchedMovie);

		return ResponseEntity.ok(MovieMapper.toResponse(savedMovie));
	}
}
