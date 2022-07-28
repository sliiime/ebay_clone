package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Validated
public class UserValidator {

    private static UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository){
        UserValidator.userRepository = userRepository;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void semanticUserValidator(ConstraintViolationException e){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This bad " + e.getMessage());
    }
    /*@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }*/

    public static void createValidator(@Valid User user) {
        String duplicateAttributes = "";
        if(userRepository.findByUsername(user.getUsername()).isPresent()) duplicateAttributes +="username, ";
        if (userRepository.findByAfm(user.getAfm()).isPresent() ) duplicateAttributes += "afm, " ;
        if (userRepository.findByPhone(user.getPhone()).isPresent() ) duplicateAttributes += "phone, ";
        if (userRepository.findByEmail(user.getEmail()).isPresent()) duplicateAttributes += "email, ";

        if (duplicateAttributes.length() != 0) throw new ResponseStatusException(HttpStatus.CONFLICT,"There already exists a user with that : " + duplicateAttributes.substring(0,duplicateAttributes.length()-2));

    }

}
