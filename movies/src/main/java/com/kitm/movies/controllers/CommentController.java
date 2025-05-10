package com.kitm.movies.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kitm.movies.dto.CommentCreateDTO;
import com.kitm.movies.dto.CommentResponseDTO;
import com.kitm.movies.dto.search.CommentIdDTO;
import com.kitm.movies.entity.Comment;
import com.kitm.movies.helpers.UserHelper;
import com.kitm.movies.mapper.CommentMapper;
import com.kitm.movies.service.CommentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	private CommentService commentService;
	private ObjectMapper objectMapper;

	@Autowired
	public CommentController(CommentService commentService, ObjectMapper objectMapper) {
		this.commentService = commentService;
		this.objectMapper = objectMapper;
	}

	@PostMapping("/search/userId")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findByUserId(@Valid @RequestBody CommentIdDTO commentIdDTO) {
		List<Comment> results = commentService.findAllByUserId(commentIdDTO.getId());
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Map.of("comments", results));
	}

	@PostMapping("/search/movieId")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> findByMovieId(@Valid @RequestBody CommentIdDTO commentIdDTO) {
		List<Comment> results = commentService.findAllByMovieId(commentIdDTO.getId());
		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Map.of("comments", results));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id) {
		Optional<Comment> results = Optional.ofNullable(commentService.findById(id));
		return results.map(comment -> ResponseEntity.ok(CommentMapper.toResponse(comment)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<CommentResponseDTO> addComment(@Valid @RequestBody CommentCreateDTO commentDTO) {
		
		Long userId = UserHelper.getCurrentUserId();

		if (commentDTO.getUserId() == null) {
			commentDTO.setUserId(userId);
		}

		//Prevents the user from creating a comment with a different userId than their own
		if (userId == null || userId != commentDTO.getUserId()) {
			return ResponseEntity.badRequest().body(null);
		}

		Comment saved = commentService.save(CommentMapper.toEntity(commentDTO));
		return ResponseEntity.ok(CommentMapper.toResponse(saved));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (!UserHelper.isUserAdmin() && !comment.getUserId().equals(UserHelper.getCurrentUserId())) {
			return ResponseEntity.status(403).body("You are not authorized to delete this comment");
		}

		commentService.deleteById(id);
		return ResponseEntity.ok("Deleted comment id - " + id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long id, @RequestBody CommentCreateDTO commentDTO) {
		Comment existingComment = commentService.findById(id);
				if (existingComment == null) {
			return ResponseEntity.notFound().build();
		}

		if (!UserHelper.isUserAdmin() && !existingComment.getUserId().equals(UserHelper.getCurrentUserId())) {
			return ResponseEntity.status(403).body(null);
		}

		Comment updatedComment = CommentMapper.toEntity(commentDTO);
		updatedComment.setId(id);
		updatedComment.setCreatedAt(existingComment.getCreatedAt());
		updatedComment.setUpdatedAt(LocalDateTime.now());

		Comment comment = commentService.save(updatedComment);

		return ResponseEntity.ok(CommentMapper.toResponse(comment));
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public ResponseEntity<CommentResponseDTO> patchComment(@PathVariable Long id, @RequestBody Map<String, Object> pathPayload) {
		Comment existingComment = commentService.findById(id);
		if (existingComment == null) {
			return ResponseEntity.notFound().build();
		}

		if (pathPayload == null || pathPayload.isEmpty() || pathPayload.containsKey("id")) {
			return ResponseEntity.badRequest().body(null);
		}

		if (!UserHelper.isUserAdmin() && !existingComment.getUserId().equals(UserHelper.getCurrentUserId())) {
			return ResponseEntity.status(403).body(null);
		}

		ObjectNode commentNode = objectMapper.convertValue(existingComment, ObjectNode.class);
		ObjectNode pathNode = objectMapper.convertValue(pathPayload, ObjectNode.class);
		commentNode.setAll(pathNode);

		Comment patchedComment = objectMapper.convertValue(commentNode, Comment.class);
		Comment savedComment = commentService.save(patchedComment);

		return ResponseEntity.ok(CommentMapper.toResponse(savedComment));
	}
}
