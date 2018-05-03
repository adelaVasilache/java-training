package javatraining.training.services.domain;

import javatraining.training.dtos.UserDto;
import javatraining.training.exceptions.DuplicateUserException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.models.User;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface UserService {
    void addUser(UserDto userDto) throws DuplicateUserException;

    void save(User user);

    User getUserByEmail(String email) throws NotFoundException;
}
