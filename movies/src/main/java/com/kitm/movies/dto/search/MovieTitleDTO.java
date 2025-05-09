package com.kitm.movies.dto.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovieTitleDTO {

	@NotBlank(message = "Title is mandatory")
	@Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
