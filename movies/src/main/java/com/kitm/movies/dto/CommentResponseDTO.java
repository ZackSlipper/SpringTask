package com.kitm.movies.dto;

import java.time.LocalDateTime;

public class CommentResponseDTO {

	private Long id;
	private Long movieId;
	private Long userId;
	private String comment;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public CommentResponseDTO() {
	}

	public CommentResponseDTO(Long id, Long movieId, Long userId, String comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.movieId = movieId;
		this.userId = userId;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
