package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUserValidator(CreateUserDto user) {
        List<String> conflicts = new ArrayList<>();

        if(userRepository.findByUsername(user.getUsername()).isPresent()) conflicts.add("username");
        if (userRepository.findByAfm(user.getAfm()).isPresent() ) conflicts.add("afm") ;
        if (userRepository.findByPhone(user.getPhone()).isPresent() ) conflicts.add("phone");
        if (userRepository.findByEmail(user.getEmail()).isPresent()) conflicts.add("email");

        if (conflicts.size() != 0) throw Responses.conflict("User",conflicts);

    }

    public void updateUserValidator(UpdateUserDto user, Integer id) {

        List<String> conflicts = new ArrayList<>();
        if (user.getUsername() != null){
            Optional<User> u = userRepository.findByUsername(user.getUsername());
            if (u.isPresent() && u.get().getId() != id) conflicts.add("username");
        }

        if (user.getPhone() != null){
            Optional<User> u = userRepository.findByPhone(user.getPhone());
            if (u.isPresent() && u.get().getId() != id) conflicts.add("phone");
        }

        if (user.getEmail() != null){
            Optional<User> u = userRepository.findByEmail(user.getUsername());
            if (u.isPresent() && u.get().getId() != id) conflicts.add("email");
        }

        if (conflicts.size() != 0) throw Responses.conflict("User",conflicts);



    }
}
