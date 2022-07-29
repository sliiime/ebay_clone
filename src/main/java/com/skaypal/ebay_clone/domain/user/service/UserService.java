package com.skaypal.ebay_clone.domain.user.service;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> Responses.notFound("User", "id", id.toString()));
    }


    public User createUser(CreateUserDto createUserDto) {


        ValidationResult validationResult = userValidator.validateCreateUserDto(createUserDto);

        if (!validationResult.isValid()) throw new ResponseStatusException(HttpStatus.CONFLICT,validationResult.getErrorMessage());

        User user = new User(createUserDto);

        return userRepository.save(user);

    }


    public void updateUser(UpdateUserDto updateUserDto) {


        ValidationResult validationResult = userValidator.validateUpdateUserDto(updateUserDto);

        if (!validationResult.isValid()) throw new ResponseStatusException(HttpStatus.CONFLICT,validationResult.getErrorMessage());

        User user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> Responses.notFound("User","id", updateUserDto.getId().toString()));


        //Checking which fields need to be updated
        if (updateUserDto.getUsername() != null)
            user.setUsername(updateUserDto.getUsername());
        if (updateUserDto.getPassword() != null)
            user.setPassword(updateUserDto.getPassword());
        if (updateUserDto.getEmail() != null)
            user.setEmail(updateUserDto.getEmail());
        if (updateUserDto.getPhone() != null)
            user.setPhone(updateUserDto.getPhone());

        userRepository.save(user);


    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> Responses.notFound("User", "id", id.toString()));
        userRepository.delete(user);
    }



}
