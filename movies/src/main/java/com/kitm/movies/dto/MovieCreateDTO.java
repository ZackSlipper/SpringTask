package com.kitm.movies.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MovieCreateDTO {

	@NotBlank(message = "Title is mandatory")
	@Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	private String title;

	@Size(max = 255, message = "Description must be less than 255 characters")
	private String description;

	@Size(max = 255, message = "Thumbnail must be less than 255 characters")
	private String thumbnail;

	@NotNull(message = "Category ID is mandatory")
	@Positive(message = "Category ID must be positive")
	private Long categoryId;

	@Positive(message = "Release year must be positive")
	@Min(value = 1900, message = "Release year must be greater than or equal to 1900")
	private Integer releaseYear;

	@DecimalMin(value = "0.0", message = "IMDB rating must be greater than or equal to 0.0")
	@DecimalMax(value = "10.0", message = "IMDB rating must be less than or equal to 10.0")
	private Double imdbRating;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}
}
