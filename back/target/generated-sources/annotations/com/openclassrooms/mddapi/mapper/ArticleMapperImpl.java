package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-15T15:07:34+0100",
    comments = "version: 1.5.1.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ArticleMapperImpl extends ArticleMapper {

    @Override
    public List<ArticleDto> toDto(List<Article> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ArticleDto> list = new ArrayList<ArticleDto>( entityList.size() );
        for ( Article article : entityList ) {
            list.add( toDto( article ) );
        }

        return list;
    }

    @Override
    public List<Article> toEntity(List<ArticleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Article> list = new ArrayList<Article>( dtoList.size() );
        for ( ArticleDto articleDto : dtoList ) {
            list.add( toEntity( articleDto ) );
        }

        return list;
    }

    @Override
    public Article toEntity(ArticleDto articleDto) {
        if ( articleDto == null ) {
            return null;
        }

        Article article = new Article();

        article.setContent( articleDto.getContent() );
        article.setCreatedAt( articleDto.getCreatedAt() );
        article.setDate( articleDto.getDate() );
        article.setId( articleDto.getId() );
        article.setTitre( articleDto.getTitre() );
        article.setUpdatedAt( articleDto.getUpdatedAt() );

        article.setComments( Optional.ofNullable(articleDto.getComments()).orElseGet(Collections::emptySet).stream().map(commmentId -> { Comment comment = this.commentService.findById(commmentId); if (comment != null) { return comment; } return null; }).collect(Collectors.toSet()) );
        article.setUser( articleDto.getUserId() != null ? this.userService.findById(articleDto.getUserId()): null );
        article.setTopic( articleDto.getTopicId() != null ? this.topicService.findById(articleDto.getTopicId()) : null );

        return article;
    }

    @Override
    public ArticleDto toDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDto.ArticleDtoBuilder articleDto = ArticleDto.builder();

        articleDto.userId( articleUserId( article ) );
        articleDto.topicId( articleTopicId( article ) );
        articleDto.content( article.getContent() );
        articleDto.createdAt( article.getCreatedAt() );
        articleDto.date( article.getDate() );
        articleDto.id( article.getId() );
        articleDto.titre( article.getTitre() );
        articleDto.updatedAt( article.getUpdatedAt() );

        articleDto.comments( Optional.ofNullable(article.getComments()).orElseGet(Collections::emptySet).stream().map(u -> u.getId()).collect(Collectors.toSet()) );

        return articleDto.build();
    }

    private Long articleUserId(Article article) {
        if ( article == null ) {
            return null;
        }
        User user = article.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long articleTopicId(Article article) {
        if ( article == null ) {
            return null;
        }
        Topic topic = article.getTopic();
        if ( topic == null ) {
            return null;
        }
        Long id = topic.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
