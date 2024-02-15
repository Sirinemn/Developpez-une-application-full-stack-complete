package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.models.Article;
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
public class TopicMapperImpl extends TopicMapper {

    @Override
    public List<TopicDto> toDto(List<Topic> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TopicDto> list = new ArrayList<TopicDto>( entityList.size() );
        for ( Topic topic : entityList ) {
            list.add( toDto( topic ) );
        }

        return list;
    }

    @Override
    public List<Topic> toEntity(List<TopicDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Topic> list = new ArrayList<Topic>( dtoList.size() );
        for ( TopicDto topicDto : dtoList ) {
            list.add( toEntity( topicDto ) );
        }

        return list;
    }

    @Override
    public Topic toEntity(TopicDto topicDto) {
        if ( topicDto == null ) {
            return null;
        }

        Topic topic = new Topic();

        topic.setId( topicDto.getId() );
        topic.setName( topicDto.getName() );

        topic.setArticles( Optional.ofNullable(topicDto.getArticles()).orElseGet(Collections::emptySet).stream().map(articleId -> { Article article = this.articleService.findById(articleId); if (article != null) { return article; } return null; }).collect(Collectors.toSet()) );
        topic.setUsers( Optional.ofNullable(topicDto.getUsers()).orElseGet(Collections::emptySet).stream().map(userId -> { User user = this.userService.findById(userId); if (user != null) { return user; } return null; }).collect(Collectors.toSet()) );

        return topic;
    }

    @Override
    public TopicDto toDto(Topic topic) {
        if ( topic == null ) {
            return null;
        }

        TopicDto.TopicDtoBuilder topicDto = TopicDto.builder();

        topicDto.id( topic.getId() );
        topicDto.name( topic.getName() );

        topicDto.articles( Optional.ofNullable(topic.getArticles()).orElseGet(Collections::emptySet).stream().map(a -> a.getId()).collect(Collectors.toSet()) );
        topicDto.users( Optional.ofNullable(topic.getUsers()).orElseGet(Collections::emptySet).stream().map(u -> u.getId()).collect(Collectors.toSet()) );

        return topicDto.build();
    }
}
