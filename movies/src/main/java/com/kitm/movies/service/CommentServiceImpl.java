package com.kitm.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitm.movies.dao.CommentRepository;
import com.kitm.movies.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment findById(Long id) {
		Optional<Comment> result = commentRepository.findById(id);
		Comment comment = null;
		if (result.isPresent()) {
			comment = result.get();
		} else {
			throw new RuntimeException("Did not find comment id - " + id);
		}
		return comment;
	}

	@Override
	public Comment save(Comment comment){
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> findAllByMovieId(Long movieId) {
		List<Comment> comments = commentRepository.findByMovieId(movieId);
		if (comments.isEmpty()) {
			throw new RuntimeException("Did not find any comments for movie id - " + movieId);
		}
		return comments;
	}

	@Override
	public List<Comment> findAllByUserId(Long userId) {
		List<Comment> comments = commentRepository.findByUserId(userId);
		if (comments.isEmpty()) {
			throw new RuntimeException("Did not find any comments for user id - " + userId);
		}
		return comments;
	}

	@Override
	public List<Comment> findAllByMovieIdAndUserId(Long movieId, Long userId) {
		List<Comment> comments = commentRepository.findByMovieIdAndUserId(movieId, userId);
		if (comments.isEmpty()) {
			throw new RuntimeException("Did not find any comments for movie id - " + movieId + " and user id - " + userId);
		}
		return comments;
	}
}
