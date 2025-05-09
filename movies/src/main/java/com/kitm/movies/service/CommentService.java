package com.kitm.movies.service;

import java.util.List;

import com.kitm.movies.entity.Comment;

public interface CommentService {
	List<Comment> findAllByMovieId(Long movieId);

	List<Comment> findAllByUserId(Long userId);

	List<Comment> findAllByMovieIdAndUserId(Long movieId, Long userId);

	Comment findById(Long id);
	
	Comment save(Comment comment);

	void deleteById(Long id);
}
