package com.kitm.movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CommentCreateDTO {

	@NotNull(message = "Movie ID is mandatory")
	@Positive(message = "Movie ID must be positive")
	private Long movieId;

	@Positive(message = "User ID must be positive")
	private Long userId;

	@NotBlank(message = "Comment is mandatory")
	@Size(min = 1, message = "Comment must be at least 1 character")
	private String comment;

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
}
