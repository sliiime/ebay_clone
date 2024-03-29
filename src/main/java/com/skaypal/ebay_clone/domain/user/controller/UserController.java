package com.skaypal.ebay_clone.domain.user.controller;


import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.ViewUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.service.UserService;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/user")
@Validated
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class UserController {

    public static final String location = "ebay_clone/api/user";

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViewUserDto>> getUsers() {
        List<ViewUserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewUserDto> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto createUserDto){
        ViewUserDto user = userService.createUser(createUserDto);
        return Responses.created(location +'/' + user.getId());
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@Valid @RequestBody UpdateUserDto updateUserDto){
        updateUserDto.setId(id);
        userService.updateUser(updateUserDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping(path = "/approve/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> approveUser(@PathVariable Integer id){
        return userService.approveUser(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

    }
}
