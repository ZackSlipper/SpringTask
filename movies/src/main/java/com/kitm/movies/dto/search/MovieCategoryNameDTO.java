package com.kitm.movies.dto.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovieCategoryNameDTO {

	@NotBlank(message = "Name is mandatory")
	@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
