package com.kitm.movies.dto;

import java.time.LocalDateTime;

public class MovieResponseDTO {

	private Long id;
	private String title;
	private String description;
	private String thumbnail;
	private Long categoryId;
	private Integer releaseYear;
	private Double imdbRating;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public MovieResponseDTO() {
	}

	public MovieResponseDTO(Long id, String title, String description, String thumbnail, Long categoryId, Integer releaseYear,
			Double imdbRating, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.categoryId = categoryId;
		this.releaseYear = releaseYear;
		this.imdbRating = imdbRating;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
