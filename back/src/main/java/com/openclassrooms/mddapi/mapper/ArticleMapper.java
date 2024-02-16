package com.openclassrooms.mddapi.mapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

@Component
@Mapper(componentModel = "spring",uses={CommentService.class}, imports = {Arrays.class, Collectors.class, Article.class, Collections.class, Optional.class, Comment.class})
public abstract class ArticleMapper implements EntityMapper<ArticleDto, Article>{
	
	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;
	@Autowired
	TopicService topicService;
	
	@Mappings({
		@Mapping(target="user", expression="java(articleDto.getUserId() != null ? this.userService.findById(articleDto.getUserId()): null)"),
		@Mapping(target="topic", expression="java(articleDto.getTopicId() != null ? this.topicService.findById(articleDto.getTopicId()) : null)"),

	})
	public abstract Article toEntity(ArticleDto articleDto);
	
	@Mappings({
		@Mapping(source="article.user.id", target="userId"),
		@Mapping(source="article.topic.id", target="topicId"),

	})
	public abstract ArticleDto toDto(Article article);

}
