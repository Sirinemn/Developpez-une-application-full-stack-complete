package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ArticleService;
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
    date = "2024-02-16T14:45:44+0100",
    comments = "version: 1.5.1.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl extends CommentMapper {

    @Override
    public List<Comment> toEntity(List<CommentDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( dtoList.size() );
        for ( CommentDto commentDto : dtoList ) {
            list.add( toEntity( commentDto ) );
        }

        return list;
    }

    @Override
    public List<CommentDto> toDto(List<Comment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( entityList.size() );
        for ( Comment comment : entityList ) {
            list.add( toDto( comment ) );
        }

        return list;
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.content( commentDto.getContent() );
        comment.id( commentDto.getId() );

        comment.user( commentDto.getUserId() != null ? this.userService.findById(commentDto.getUserId()) : null );
        comment.article( commentDto.getArticleId() != null ? this.articleService.findById(commentDto.getArticleId()) : null );

        return comment.build();
    }

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto.CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.userId( commentUserId( comment ) );
        commentDto.articleId( commentArticleId( comment ) );
        commentDto.content( comment.getContent() );
        commentDto.id( comment.getId() );

        return commentDto.build();
    }

    private Long commentUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentArticleId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Article article = comment.getArticle();
        if ( article == null ) {
            return null;
        }
        Long id = article.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
