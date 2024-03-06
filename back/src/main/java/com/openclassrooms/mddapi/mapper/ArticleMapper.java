package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

@Component
@Mapper(componentModel = "spring")
public abstract class ArticleMapper implements EntityMapper<ArticleDto, Article> {

	@Autowired
	UserService userService;
	@Autowired
	TopicService topicService;

	@Mappings({
			@Mapping(target = "user", expression = "java(articleDto.getUserId() != null ? this.userService.findById(articleDto.getUserId()): null)"),
			@Mapping(target = "topic", expression = "java(articleDto.getTopicId() != null ? this.topicService.findById(articleDto.getTopicId()) : null)"),
			@Mapping(target = "updatedAt", ignore = true)

	})
	public abstract Article toEntity(ArticleDto articleDto);

	@Mappings({ @Mapping(source = "article.topic.name", target = "topicName"),
			@Mapping(source = "article.user.name", target = "userName"),
			@Mapping(source = "article.topic.id", target = "topicId"),
			@Mapping(expression = "java(article.getUser().getId())", target = "userId"),

	})
	public abstract ArticleDto toDto(Article article);

}
