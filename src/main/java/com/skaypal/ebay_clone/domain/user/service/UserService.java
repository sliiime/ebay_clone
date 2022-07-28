package com.skaypal.ebay_clone.domain.user.service;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserValidator  userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }


    public User createUser(CreateUserDto createUserDto) {


        User user = new User(createUserDto);

        userValidator.createUserValidator(user);

        return userRepository.save(user);


    }
}
