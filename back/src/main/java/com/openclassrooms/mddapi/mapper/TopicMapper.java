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

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.UserService;


@Component
@Mapper(componentModel = "spring", imports = {Arrays.class, Collectors.class, Article.class, Collections.class, Optional.class, User.class})
public abstract class TopicMapper implements EntityMapper<TopicDto, Topic>{
	@Autowired
	ArticleService articleService;
	@Autowired
	UserService userService;
	
	@Mappings({
	@Mapping(target="articles", expression="java(Optional.ofNullable(topicDto.getArticles()).orElseGet(Collections::emptyList).stream().map(articleId -> { Article article = this.articleService.findById(articleId); if (article != null) { return article; } return null; }).collect(Collectors.toList()))"),
	@Mapping(target="users", expression="java(Optional.ofNullable(topicDto.getUsers()).orElseGet(Collections::emptyList).stream().map(userId -> { User user = this.userService.findById(userId); if (user != null) { return user; } return null; }).collect(Collectors.toList()))"),
	})
	public abstract Topic toEntity(TopicDto topicDto);
	
	@Mappings({
	@Mapping(target="articles", expression="java(Optional.ofNullable(topic.getArticles()).orElseGet(Collections::emptyList).stream().map(a -> a.getId()).collect(Collectors.toList()))"),
	@Mapping(target="users", expression="java(Optional.ofNullable(topic.getUsers()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
	})
	public abstract TopicDto toDto(Topic topic);


}
