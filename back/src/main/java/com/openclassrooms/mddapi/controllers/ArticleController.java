package com.openclassrooms.mddapi.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.payload.response.ArticleResponse;
import com.openclassrooms.mddapi.services.ArticleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ArticleController {
	private final ArticleService articleService;
	private final ArticleMapper articleMapper;
	public ArticleController(ArticleMapper articleMapper, ArticleService articleService) {
		this.articleMapper = articleMapper;
		this.articleService = articleService;				
	}
	
	@GetMapping("/article/{id}")
	public ResponseEntity<ArticleDto> findById(@PathVariable Long id){
		Article article = this.articleService.findById(id);
		ArticleDto articlesDto = articleMapper.toDto(article);
		return ResponseEntity.ok(articlesDto);
		
	}
	@GetMapping("/article/topic/{id}")
	public ResponseEntity<ArticleResponse> findByTopic(@PathVariable Long id){
		Set<Article> articles = (Set<Article>) this.articleService.getArticlesByTopicId(id);
		Set<ArticleDto> articlesDto = new HashSet<>();
		articles.forEach(article -> articlesDto.add(articleMapper.toDto(article)));
		ArticleResponse articleResponse = new ArticleResponse(articlesDto);
		return ResponseEntity.ok(articleResponse);
		
	}
}
