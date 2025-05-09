package com.kitm.movies.dto.search;

import jakarta.validation.constraints.Positive;

public class MovieCategoryIdDTO {

	@Positive(message = "Category ID must be positive")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
