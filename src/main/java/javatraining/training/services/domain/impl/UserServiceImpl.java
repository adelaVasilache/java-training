package javatraining.training.services.domain.impl;

import javatraining.training.dtos.UserDto;
import javatraining.training.exceptions.DuplicateUserException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.mappers.UserMapper;
import javatraining.training.models.User;
import javatraining.training.repositories.UserRepository;
import javatraining.training.services.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = UserMapper.INSTANCE;
    }

    @Override
    public User getUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }


    @Override
    public void addUser(UserDto userDto) throws DuplicateUserException {
        if (checkIfUserExists(userDto.getEmail())) {
            throw new DuplicateUserException(userDto.getEmail());
        }
        User user = userMapper.toUserWithPassword(userDto, bCryptPasswordEncoder.encode(userDto.getPassword()));
        save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    private boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
