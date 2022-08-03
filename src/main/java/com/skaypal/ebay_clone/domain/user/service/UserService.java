package com.skaypal.ebay_clone.domain.user.service;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.ViewUserDto;
import com.skaypal.ebay_clone.domain.user.exceptions.UserConflictException;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ViewUserDto> getUsers() {
        return userRepository.findAll().stream().map((u) -> new ViewUserDto(u)).collect(Collectors.toList());
    }

    public ViewUserDto getUser(Integer id) throws UserNotFoundException{

        return new ViewUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id.toString())));
    }


    public ViewUserDto createUser(CreateUserDto createUserDto) throws UserConflictException {


        ValidationResult validationResult = userValidator.validateCreateUserDto(createUserDto);

        if (!validationResult.isValid()) throw new UserConflictException(validationResult.getErrorMessage());

        User user = new User(createUserDto);

        return new ViewUserDto(userRepository.save(user));

    }


    public void updateUser(UpdateUserDto updateUserDto) throws UserConflictException, UserNotFoundException{


        ValidationResult validationResult = userValidator.validateUpdateUserDto(updateUserDto);

        if (!validationResult.isValid()) throw new UserConflictException(validationResult.getErrorMessage());

        User user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new UserNotFoundException("id", updateUserDto.getId().toString()));



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

    public void deleteUser(Integer id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id.toString()));
        userRepository.delete(user);
    }



}
