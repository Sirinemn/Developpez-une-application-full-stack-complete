package com.openclassrooms.mddapi.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final TopicRepository topicRepository;
	private final ArticleRepository articleRepository;

	public UserService(UserRepository userRepository, TopicRepository topicRepository,
			ArticleRepository articleRepository) {
		this.userRepository = userRepository;
		this.topicRepository = topicRepository;
		this.articleRepository = articleRepository;
	}

	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}

	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email).orElse(null);
	}

	public void updateUser(String name, String email, final Long id) {
		LocalDateTime now = LocalDateTime.now();
		User user = userRepository.findById(id).orElse(null);
		user.setName(name);
		user.setEmail(email);
		user.setUpdatedAt(now);
		userRepository.save(user);
	}

	public User getUserByName(String name) {
		User user = userRepository.findByName(name).orElse(null);
		return user;
	}

	public Set<Article> getArticles(Long id) {
		User user = userRepository.findById(id).orElse(null);
		Set<Topic> topics = user.getTopics();
		Set<Article> articles = new HashSet<>();
		topics.forEach(topic -> articles.addAll(articleRepository.findByTopicId(topic.getId())));
		return articles;
	}

	public void subscribe(Long id, Long topicId) {
		User user = userRepository.findById(id).orElse(null);
		Topic topic = topicRepository.findById(topicId).orElse(null);
		if (user == null || topic == null) {
			throw new NotFoundException();
		}
		boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topicId));
		if (alreadySubscribe) {
			throw new BadRequestException();
		}
		user.getTopics().add(topic);
		this.userRepository.save(user);

	}

	public void unsubscribe(Long id, Long topicId) {
		User user = userRepository.findById(id).orElse(null);
		Topic topic = topicRepository.findById(topicId).orElse(null);
		if (user == null || topic == null) {
			throw new NotFoundException();
		}
		boolean alreadySubscribe = user.getTopics().stream().anyMatch(o -> o.getId().equals(topic.getId()));
		if (!alreadySubscribe) {
			throw new BadRequestException();
		}
		user.setTopics((Set<Topic>) user.getTopics().stream().filter(topics -> !topics.getId().equals(topicId))
				.collect(Collectors.toSet()));
		this.userRepository.save(user);

	}

}
