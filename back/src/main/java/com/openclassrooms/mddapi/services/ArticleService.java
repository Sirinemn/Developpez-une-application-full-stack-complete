package com.openclassrooms.mddapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArticleService {
	private final ArticleRepository articleRepository;
	
	 public ArticleService(ArticleRepository articleRepository) {
		 this.articleRepository = articleRepository;
	}
	
	public void saveArticle(Article article) {
		LocalDateTime now = LocalDateTime.now();
		article.setCreatedAt(now);
		 articleRepository.save(article);
	}
	public List<Article> findAll(){
		return articleRepository.findAll();
	}
	
	public Article findById(Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	public Set<Article> getArticlesByTopicId(Long topicId){
		return articleRepository.findByTopicId(topicId);
		
	}
}
