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

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.UserService;

@Component
@Mapper(componentModel = "spring", imports = {Arrays.class, Collectors.class, Article.class, Collections.class, Optional.class, Comment.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment>{
	
	@Autowired
	ArticleService articleService;
	@Autowired
	UserService userService;

	
	@Mappings({
		@Mapping(target="user", expression="java(commentDto.getUserId() != null ? this.userService.findById(commentDto.getUserId() : null)"),
		@Mapping(target="article", expression="java(commentDto.getArticleId() != null ? this.articleService.findById(commentDto.getArticleId()) : null)"),

	})
	public abstract Comment toEntity(CommentDto commentDto);
	
	@Mappings({
		@Mapping(source="comment.user.id", target="userId"),
		@Mapping(source="comment.article.id", target="articleId"),

	})
	public abstract CommentDto toDto(Comment comment);


}
