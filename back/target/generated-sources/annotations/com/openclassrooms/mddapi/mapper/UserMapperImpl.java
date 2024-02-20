package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Role;
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
    date = "2024-02-20T13:07:48+0100",
    comments = "version: 1.5.1.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String email = null;
        String firstName = null;
        String lastName = null;
        String password = null;

        email = userDto.getEmail();
        firstName = userDto.getFirstName();
        lastName = userDto.getLastName();
        password = userDto.getPassword();

        User user = new User( email, lastName, firstName, password );

        user.setCreatedAt( userDto.getCreatedAt() );
        user.setId( userDto.getId() );
        user.setUpdatedAt( userDto.getUpdatedAt() );

        user.setComments( Optional.ofNullable(userDto.getComments()).orElseGet(Collections::emptySet).stream().map(commmentId -> { Comment comment = this.commentService.findById(commmentId); if (comment != null) { return comment; } return null; }).collect(Collectors.toSet()) );
        user.setArticles( Optional.ofNullable(userDto.getArticles()).orElseGet(Collections::emptySet).stream().map(articleId -> { Article article = this.articleService.findById(articleId); if (article != null) { return article; } return null; }).collect(Collectors.toSet()) );
        user.setRoles( Optional.ofNullable(userDto.getRoles()).orElseGet(Collections::emptyList).stream().map(roleId -> { Role role = this.roleServiceO.findById(roleId); if (role != null) { return role; } return null; }).collect(Collectors.toList()) );
        user.setTopics( Optional.ofNullable(userDto.getTopics()).orElseGet(Collections::emptySet).stream().map(topicId -> { Topic topic = this.topicService.findById(topicId); if (topic != null) { return topic; } return null; }).collect(Collectors.toSet()) );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.createdAt( user.getCreatedAt() );
        userDto.email( user.getEmail() );
        userDto.firstName( user.getFirstName() );
        userDto.id( user.getId() );
        userDto.lastName( user.getLastName() );
        userDto.password( user.getPassword() );
        userDto.updatedAt( user.getUpdatedAt() );

        userDto.comments( Optional.ofNullable(user.getComments()).orElseGet(Collections::emptySet).stream().map(c -> c.getId()).collect(Collectors.toSet()) );
        userDto.articles( Optional.ofNullable(user.getArticles()).orElseGet(Collections::emptySet).stream().map(a -> a.getId()).collect(Collectors.toSet()) );
        userDto.roles( Optional.ofNullable(user.getRoles()).orElseGet(Collections::emptyList).stream().map(r -> r.getId()).collect(Collectors.toList()) );
        userDto.topics( Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptySet).stream().map(t -> t.getId()).collect(Collectors.toSet()) );

        return userDto.build();
    }
}
