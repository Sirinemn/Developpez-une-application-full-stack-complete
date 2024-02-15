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

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Role;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.RoleService;
import com.openclassrooms.mddapi.services.TopicService;

@Component
@Mapper(componentModel = "spring", imports = {Arrays.class, Collectors.class, Article.class, Collections.class, Optional.class, Comment.class, Topic.class, Role.class})
public abstract class UserMapper implements EntityMapper<UserDto, User>{
	@Autowired
	CommentService commentService;
	@Autowired
	ArticleService articleService;
	@Autowired
	TopicService topicService;
	@Autowired
	RoleService roleServiceO;
	
	@Mappings({
		@Mapping(target="comments", expression="java(Optional.ofNullable(userDto.getComments()).orElseGet(Collections::emptySet).stream().map(commmentId -> { Comment comment = this.commentService.findById(commmentId); if (comment != null) { return comment; } return null; }).collect(Collectors.toSet()))"),
		@Mapping(target="articles", expression="java(Optional.ofNullable(userDto.getArticles()).orElseGet(Collections::emptySet).stream().map(articleId -> { Article article = this.articleService.findById(articleId); if (article != null) { return article; } return null; }).collect(Collectors.toSet()))"),
		@Mapping(target="roles", expression="java(Optional.ofNullable(userDto.getRoles()).orElseGet(Collections::emptyList).stream().map(roleId -> { Role role = this.roleServiceO.findById(roleId); if (role != null) { return role; } return null; }).collect(Collectors.toList()))"),
		@Mapping(target="topics", expression="java(Optional.ofNullable(userDto.getTopics()).orElseGet(Collections::emptySet).stream().map(topicId -> { Topic topic = this.topicService.findById(topicId); if (topic != null) { return topic; } return null; }).collect(Collectors.toSet()))"),

	})
	public abstract User toEntity(UserDto userDto);
	
	@Mappings({
		@Mapping(target="comments", expression="java(Optional.ofNullable(user.getComments()).orElseGet(Collections::emptySet).stream().map(c -> c.getId()).collect(Collectors.toSet()))"),
		@Mapping(target="articles", expression="java(Optional.ofNullable(user.getArticles()).orElseGet(Collections::emptySet).stream().map(a -> a.getId()).collect(Collectors.toSet()))"),
		@Mapping(target="roles", expression="java(Optional.ofNullable(user.getRoles()).orElseGet(Collections::emptyList).stream().map(r -> r.getId()).collect(Collectors.toList()))"),
		@Mapping(target="topics", expression="java(Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptySet).stream().map(t -> t.getId()).collect(Collectors.toSet()))")

	})
	public abstract UserDto toDto(User user);


}
