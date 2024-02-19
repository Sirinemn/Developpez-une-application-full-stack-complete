package com.openclassrooms.mddapi.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{

	Set<Article> findByTopicId(Long topicId);

}
