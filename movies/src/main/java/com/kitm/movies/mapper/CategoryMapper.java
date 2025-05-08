package com.kitm.movies.mapper;

import java.time.LocalDateTime;

import com.kitm.movies.dto.CategoryCreateDTO;
import com.kitm.movies.dto.CategoryResponseDTO;
import com.kitm.movies.entity.Category;

public class CategoryMapper {
	public static Category toEntity(CategoryCreateDTO dto) {
		Category category = new Category();

		category.setName(dto.getName());
		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());

		return category;
	}

	public static CategoryResponseDTO toResponse(Category category) {
		CategoryResponseDTO dto = new CategoryResponseDTO();

		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setCreatedAt(category.getCreatedAt());
		dto.setUpdatedAt(category.getUpdatedAt());

		return dto;
	}
}
