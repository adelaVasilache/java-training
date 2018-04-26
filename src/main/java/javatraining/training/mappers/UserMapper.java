package javatraining.training.mappers;

import javatraining.training.dtos.UserCommentDto;
import javatraining.training.dtos.UserDto;
import javatraining.training.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    public abstract User toUser(UserDto userDto);

    public User toUserWithPassword(UserDto userDto, String encodedPassword){
        User user = toUser(userDto);
        user.setPassword(encodedPassword);

        return user;
    }

    public abstract UserCommentDto toUserCommentDto(User user);

}
