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
import com.kitm.movies.dto.CategoryResponseDTO;
import com.kitm.movies.dto.CategoryCreateDTO;
import com.kitm.movies.entity.Category;
import com.kitm.movies.mapper.CategoryMapper;
import com.kitm.movies.service.CategoryService;

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
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private CategoryService categoryService;
	private ObjectMapper objectMapper;

	@Autowired
	public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
		this.categoryService = categoryService;
		this.objectMapper = objectMapper;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findAll() {

		List<CategoryResponseDTO> categories = categoryService.findAll()
			.stream()
			.map(CategoryMapper::toResponse)
			.collect(Collectors.toList());
		
		Map<String, Object> response = Map.of(
			"status", "Success",
			"timestamp", System.currentTimeMillis(),
			"data", categories
		);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
		Optional<Category> results = Optional.ofNullable(categoryService.findById(id));
		return results.map(category -> ResponseEntity.ok(CategoryMapper.toResponse(category)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<CategoryResponseDTO> addCategory(@Valid @RequestBody CategoryCreateDTO categoryDTO) {
		Category saved = categoryService.save(CategoryMapper.toEntity(categoryDTO));
		return ResponseEntity.ok(CategoryMapper.toResponse(saved));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		Category category = categoryService.findById(id);
		if (category == null) {
			return ResponseEntity.notFound().build();
		}

		categoryService.deleteById(id);
		return ResponseEntity.ok("Deleted category id - " + id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryCreateDTO categoryDTO) {
		Category existingCategory = categoryService.findById(id);
		if (existingCategory == null) {
			return ResponseEntity.notFound().build();
		}

		Category updatedCategory = CategoryMapper.toEntity(categoryDTO);
		updatedCategory.setId(id);
		updatedCategory.setCreatedAt(existingCategory.getCreatedAt());
		updatedCategory.setUpdatedAt(LocalDateTime.now());

		Category category = categoryService.save(updatedCategory);

		return ResponseEntity.ok(CategoryMapper.toResponse(category));
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<CategoryResponseDTO> patchCategory(@PathVariable Long id, @RequestBody Map<String, Object> pathPayload) {
		Category existingCategory = categoryService.findById(id);
		if (existingCategory == null) {
			return ResponseEntity.notFound().build();
		}

		if (pathPayload == null || pathPayload.isEmpty() || pathPayload.containsKey("id")) {
			return ResponseEntity.badRequest().body(null);
		}

		ObjectNode categoryNode = objectMapper.convertValue(existingCategory, ObjectNode.class);
		ObjectNode pathNode = objectMapper.convertValue(pathPayload, ObjectNode.class);
		categoryNode.setAll(pathNode);

		Category patchedCategory = objectMapper.convertValue(categoryNode, Category.class);
		Category savedCategory = categoryService.save(patchedCategory);

		return ResponseEntity.ok(CategoryMapper.toResponse(savedCategory));
	}
}
