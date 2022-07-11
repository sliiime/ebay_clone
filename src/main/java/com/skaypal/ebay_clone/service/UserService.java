package com.skaypal.ebay_clone.service;

import com.skaypal.ebay_clone.UserRegStatus;
import com.skaypal.ebay_clone.model.User;
import com.skaypal.ebay_clone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
