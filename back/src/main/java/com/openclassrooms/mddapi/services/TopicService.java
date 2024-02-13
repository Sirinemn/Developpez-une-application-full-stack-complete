package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
@Transactional
public class TopicService {
	public final TopicRepository topicRepository;
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	
	public List<Topic> findAll(){
		return topicRepository.findAll();
	}
	public Optional<Topic> findById(Long id) {
		return topicRepository.findById(id);
	}

}
