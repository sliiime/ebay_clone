package com.skaypal.ebay_clone.domain.user.controller;


import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
         public ResponseEntity<List<User>> getUsers(){
            List<User> users = userService.getUsers();
            return ResponseEntity.ok(users);
         }

         @GetMapping(path = "/{id}")
        public ResponseEntity<User> getUser(@PathVariable Integer id){
            return ResponseEntity.ok(userService.getUser(id));

         }


}
