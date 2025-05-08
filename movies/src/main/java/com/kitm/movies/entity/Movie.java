package com.kitm.movies.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "release_year")
	private Integer releaseYear;

	@Column(name = "imdb_rating")
	private Double imdbRating;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Movie() {
	}

	public Movie(String title, String description, String thumbnail, Long categoryId, Integer releaseYear, Double imdbRating) {
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.categoryId = categoryId;
		this.releaseYear = releaseYear;
		this.imdbRating = imdbRating;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
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

	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", thumbnail='" + thumbnail + '\'' +
				", categoryId=" + categoryId +
				", releaseYear=" + releaseYear +
				", imdbRating=" + imdbRating +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
