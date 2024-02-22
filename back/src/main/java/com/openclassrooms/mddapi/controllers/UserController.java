package com.openclassrooms.mddapi.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.response.ArticleResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.services.UserService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;
	private final ArticleMapper articleMapper;

	public UserController(UserMapper userMapper,
			UserService userService, ArticleMapper articleMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
		this.articleMapper = articleMapper;
	}
	@GetMapping("/me")
	@ResponseBody
	public ResponseEntity<UserDto> currentUserName(Authentication authentication) {
		String name = authentication.getName();
		User user = userService.getUserByName(name);
		return ResponseEntity.ok(this.userMapper.toDto(user));
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable String id) {
		User user = userService.findById(Long.parseLong(id));
		
		return ResponseEntity.ok(this.userMapper.toDto(user));
	}
	@GetMapping("/user/{id}/topics")
	public ResponseEntity<?> getSubscribedTopics(@PathVariable String id){
		User user = userService.findById(Long.parseLong(id));
		return ResponseEntity.ok(user.getTopics());
		
	}
	@GetMapping("/user/{id}/articles")
	public ResponseEntity<ArticleResponse> getSubscribedArticles(@PathVariable String id){
		Set<Article> articles=userService.getArticles(Long.parseLong(id));
		Set<ArticleDto> articlesDto = new HashSet<>();
		articles.forEach(article-> articlesDto.add(articleMapper.toDto(article)));
		ArticleResponse articleResponse = new ArticleResponse(articlesDto);
		return new ResponseEntity<>(articleResponse, HttpStatus.OK);
		
	}
	@PutMapping("/user/update/{id}")
	public ResponseEntity<MessageResponse> updateUser(@RequestParam("name") @NotBlank @Size(max = 63) String name, @RequestParam("email") @NotBlank @Size(max = 63) String email, @PathVariable Long id){
		userService.updateUser(name, email, id);
		MessageResponse messageResponse = new MessageResponse("Updated with success!");
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		
	}
	@PostMapping("{id}/subscribe/{topicId}")
	public ResponseEntity<?> subscribe(@PathVariable("id") String id, @PathVariable("topicId") String topicId){
		   try {
	            this.userService.subscribe(Long.parseLong(id), Long.parseLong(topicId));

	            return ResponseEntity.ok().build();
	        } catch (NumberFormatException e) {
	            return ResponseEntity.badRequest().build();
	        }	
	}
	@PostMapping("{id}/unsubscribe/{topicId}")
	public ResponseEntity<?> unsubscribe(@PathVariable("id") String id, @PathVariable("topicId") String topicId){
		   try {
	            this.userService.unsubscribe(Long.parseLong(id), Long.parseLong(topicId));

	            return ResponseEntity.ok().build();
	        } catch (NumberFormatException e) {
	            return ResponseEntity.badRequest().build();
	        }	
	}

}
