package javatraining.training.mappers;

import javatraining.training.dtos.UserCommentDto;
import javatraining.training.dtos.UserDto;
import javatraining.training.models.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-05-03T14:46:28+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class UserMapperImpl extends UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userDto.getEmail() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );

        return user;
    }

    @Override
    public UserCommentDto toUserCommentDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserCommentDto userCommentDto = new UserCommentDto();

        userCommentDto.setEmail( user.getEmail() );
        userCommentDto.setFirstName( user.getFirstName() );
        userCommentDto.setLastName( user.getLastName() );

        return userCommentDto;
    }
}
