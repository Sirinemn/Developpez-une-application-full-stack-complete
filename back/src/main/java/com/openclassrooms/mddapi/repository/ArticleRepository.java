package com.openclassrooms.mddapi.repository;

import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{

	List<Article> findByTopicId(Long topicId);

}
