package com.openclassrooms.mddapi.services;


import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}
	public Comment findById(Long id) {
		return commentRepository.findById(id).orElse(null);
	}
}
