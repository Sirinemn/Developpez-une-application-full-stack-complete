package com.openclassrooms.mddapi.services;

import java.util.List;


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
		articleRepository.save(article);
	}
	public List<Article> findAll(){
		return articleRepository.findAll();
	}
	
	public Article findById(Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	public List<Article> getArticlesByTopicId(Long topicId){
		return articleRepository.findByTopicId(topicId);
		
	}
}
