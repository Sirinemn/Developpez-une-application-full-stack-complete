package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.payload.response.TopicsResponse;
import com.openclassrooms.mddapi.services.TopicService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TopicController {
	private final TopicService topicService;
	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@GetMapping("/topics")
	public ResponseEntity<TopicsResponse> getAllTopics(){
		List<Topic> topics = topicService.findAll();
		TopicsResponse topicsResponse = new TopicsResponse(topics);
		return ResponseEntity.ok(topicsResponse);
	}

}
