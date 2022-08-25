package com.skaypal.ebay_clone.domain.auth.controller;


import com.skaypal.ebay_clone.domain.auth.dto.LoginFormDto;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ebay_clone/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> loginHandler(@RequestBody LoginFormDto loginFormDto) {

        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginFormDto.getUsername(),
                loginFormDto.getPassword());

        authenticationManager.authenticate(authInputToken);
        Optional<User> user = userRepository.findByUsername(loginFormDto.getUsername());

        String token = jwtUtil.generateToken(user.orElseThrow(() -> new UserNotFoundException("username",loginFormDto.getUsername())));

        return ResponseEntity.ok(token);
    }

}
