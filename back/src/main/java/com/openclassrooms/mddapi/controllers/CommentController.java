package com.openclassrooms.mddapi.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.payload.response.CommentsResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.services.CommentService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
	private final CommentService commentService;
	private final CommentMapper commentMapper;
	public CommentController(CommentService commentService, CommentMapper commentMapper) {
		this.commentService = commentService;
		this.commentMapper = commentMapper;
		
	}
	 @PostMapping()
	    public ResponseEntity<MessageResponse> create(@RequestBody CommentDto commentDto) {

		 this.commentService.saveComment(this.commentMapper.toEntity(commentDto));
		 MessageResponse messageResponse = new MessageResponse("Created with success!");
		 return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	    }
	@GetMapping("/{id}")
	public ResponseEntity<CommentsResponse> getCommentByArticle(@PathVariable String id){
		List<Comment> comments=commentService.findByArticleId(Long.parseLong(id));
		List<CommentDto> commentsDto = new ArrayList<>();
		comments.forEach(comment-> commentsDto.add(commentMapper.toDto(comment)));
		CommentsResponse commentResponse = new CommentsResponse(commentsDto);
		return new ResponseEntity<>(commentResponse, HttpStatus.OK);
		
	}
}
