package com.openclassrooms.mddapi.services;

import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService {
	   private final UserRepository userRepository;
	   private final TopicRepository topicRepository;

	    public UserService(UserRepository userRepository, TopicRepository topicRepository) {
	        this.userRepository = userRepository;
	        this.topicRepository = topicRepository;
	    }

	    public void delete(Long id) {
	        this.userRepository.deleteById(id);
	    }

	    public User findById(Long id) {
	        return this.userRepository.findById(id).orElse(null);
	    }
	    public void subscribe(Long id, Long topicId) {
	    	User user = userRepository.findById(id).orElse(null);
	    	Topic topic = topicRepository.findById(topicId).orElse(null);
	    	if(user == null || topic == null) {
	    		throw new NotFoundException();
	    	}
	    	boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topicId));
	        if(alreadySubscribe) {
	            throw new BadRequestException();
	        }
	    	user.getTopics().add(topic);
	    	this.userRepository.save(user);

	    }
	    public void unsubscribe(Long id, Long topicId) {
	    	User user = userRepository.findById(id).orElse(null);
	    	Topic topic = topicRepository.findById(topicId).orElse(null);
	    	if(user == null || topic == null) {
	    		throw new NotFoundException();
	    	}
	    	boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topic.getId()));
	        if(alreadySubscribe) {
	            throw new BadRequestException();
	        }
	    	user.setTopics((Set<Topic>) user.getTopics().stream().filter(topics -> !topics.getId().equals(topicId)).collect(Collectors.toSet()));
	    	this.userRepository.save(user);

	    }

}
