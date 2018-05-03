package javatraining.training.controllers;

import javatraining.training.dtos.UserDto;
import javatraining.training.exceptions.DuplicateUserException;
import javatraining.training.services.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) throws DuplicateUserException {
        userService.addUser(userDto);
        return new ResponseEntity<>(UserDto.builder().firstName(userDto.getFirstName()).
                lastName(userDto.getLastName()).build(), HttpStatus.OK);
    }
}
