package com.kitm.movies.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitm.movies.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByMovieId(Long id);
	List<Comment> findByUserId(Long id);
	List<Comment> findByMovieIdAndUserId(Long movieId, Long userId);
}
