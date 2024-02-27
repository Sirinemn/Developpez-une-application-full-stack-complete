package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TopicService {
	public final TopicRepository topicRepository;
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	
	public List<Topic> findAll(){
		return  topicRepository.findAll();
	}
	public Topic findById(Long id) {
		return topicRepository.findById(id).orElse(null);
	}
	public void create(Topic topic) {
		topicRepository.save(topic);
	}
}
