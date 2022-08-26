package com.skaypal.ebay_clone.domain.auth.service;

import com.skaypal.ebay_clone.domain.auth.dto.AuthenticationResult;
import com.skaypal.ebay_clone.domain.auth.dto.LoginFormDto;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import com.skaypal.ebay_clone.utils.validator.ServiceResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthService {

    JWTUtil jwtUtil;
    UserRepository userRepository;
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(JWTUtil jwtUtil,
                       UserRepository userRepository,
                       AuthenticationManager authenticationManager){

        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResult authenticate(LoginFormDto loginFormDto){
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginFormDto.getUsername(),
                loginFormDto.getPassword());

        authenticationManager.authenticate(authInputToken);
        Optional<User> user = userRepository.findByUsername(loginFormDto.getUsername());

        return user.isPresent() ?
                AuthenticationResult.of(jwtUtil.generateToken(user.get())) :
                AuthenticationResult.of(ServiceResultStatus.NOT_FOUND);


    }
}
