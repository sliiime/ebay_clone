package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;

@Component
public class UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUserValidator(User user) {
        String duplicateAttributes = "";
        if(userRepository.findByUsername(user.getUsername()).isPresent()) duplicateAttributes +="username, ";
        if (userRepository.findByAfm(user.getAfm()).isPresent() ) duplicateAttributes += "afm, " ;
        if (userRepository.findByPhone(user.getPhone()).isPresent() ) duplicateAttributes += "phone, ";
        if (userRepository.findByEmail(user.getEmail()).isPresent()) duplicateAttributes += "email, ";

        if (duplicateAttributes.length() != 0) throw new ResponseStatusException(HttpStatus.CONFLICT,"There already exists a user with that : " + duplicateAttributes.substring(0,duplicateAttributes.length()-2));

    }

}
