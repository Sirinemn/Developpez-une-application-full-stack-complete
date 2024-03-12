package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T13:36:57+0100",
    comments = "version: 1.5.1.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( dto.getEmail() );
        user.setName( dto.getName() );
        user.setUpdatedAt( dto.getUpdatedAt() );
        Set<Topic> set = dto.getTopics();
        if ( set != null ) {
            user.setTopics( new LinkedHashSet<Topic>( set ) );
        }
        user.setId( dto.getId() );
        user.setCreatedAt( dto.getCreatedAt() );

        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.createdAt( entity.getCreatedAt() );
        userDto.email( entity.getEmail() );
        userDto.id( entity.getId() );
        userDto.name( entity.getName() );
        Set<Topic> set = entity.getTopics();
        if ( set != null ) {
            userDto.topics( new LinkedHashSet<Topic>( set ) );
        }
        userDto.updatedAt( entity.getUpdatedAt() );

        return userDto.build();
    }

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
}
