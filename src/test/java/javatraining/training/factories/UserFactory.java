package javatraining.training.factories;

import javatraining.training.dtos.UserCommentDto;
import javatraining.training.dtos.UserDto;
import javatraining.training.models.User;

import static javatraining.training.constants.UserConstants.EMAIL;
import static javatraining.training.constants.UserConstants.PASSWORD;
import static javatraining.training.constants.UserConstants.FIRST_NAME;
import static javatraining.training.constants.UserConstants.LAST_NAME;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class UserFactory {
    public static UserDto createUserDto(String email, String firstName, String lastName, String password){
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setPassword(password);
        return userDto;
    }

    public static UserDto createUserDto(){
        UserDto userDto = new UserDto();
        userDto.setEmail(EMAIL);
        userDto.setEmail(PASSWORD);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        return userDto;
    }

    public static User createUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        return user;
    }

    public static UserCommentDto createUserCommentDto(){
        return UserCommentDto.builder().email(EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME).build();
    }
}
