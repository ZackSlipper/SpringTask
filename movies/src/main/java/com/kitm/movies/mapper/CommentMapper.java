package com.kitm.movies.mapper;

import java.time.LocalDateTime;

import com.kitm.movies.dto.CommentCreateDTO;
import com.kitm.movies.dto.CommentResponseDTO;
import com.kitm.movies.entity.Comment;

public class CommentMapper {
	public static Comment toEntity(CommentCreateDTO dto) {
		Comment comment = new Comment();

		comment.setMovieId(dto.getMovieId());
		comment.setUserId(dto.getUserId());
		comment.setComment(dto.getComment());
		comment.setCreatedAt(LocalDateTime.now());
		comment.setUpdatedAt(LocalDateTime.now());

		return comment;
	}
	
	public static CommentResponseDTO toResponse(Comment comment) {
		CommentResponseDTO dto = new CommentResponseDTO();
		
		dto.setId(comment.getId());
		dto.setMovieId(comment.getMovieId());
		dto.setUserId(comment.getUserId());
		dto.setComment(comment.getComment());
		dto.setCreatedAt(comment.getCreatedAt());
		dto.setUpdatedAt(comment.getUpdatedAt());

		return dto;
	}
}
