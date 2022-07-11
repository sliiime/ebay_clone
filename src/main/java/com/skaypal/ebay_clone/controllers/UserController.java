package com.skaypal.ebay_clone.controllers;


import com.skaypal.ebay_clone.UserRegStatus;
import com.skaypal.ebay_clone.model.User;
import com.skaypal.ebay_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/user")
public class UserController {

        private final UserService userService;


        @Autowired
        public UserController(UserService userService){
            this.userService = userService;
        }

         @GetMapping
         public List<User> getUsers(){
            return userService.getUsers();
         }


}
