package com.skaypal.ebay_clone.domain.auth.controller;


import com.skaypal.ebay_clone.domain.auth.dto.AuthenticationResult;
import com.skaypal.ebay_clone.domain.auth.dto.LoginFormDto;
import com.skaypal.ebay_clone.domain.auth.service.AuthService;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import com.skaypal.ebay_clone.utils.validator.ServiceResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ebay_clone/api/auth")
public class AuthController {
    AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> loginHandler(@RequestBody LoginFormDto loginFormDto) {

        AuthenticationResult<HashMap<String, Object>> hopefullyMap = authService.authenticate(loginFormDto);

        return ServiceResultStatus.OK.equals(hopefullyMap.getServiceResultStatus()) ?
                ResponseEntity.ok(hopefullyMap.hopefully()) :
                ResponseEntity.badRequest().build();
        //Needs to provide a more explanatory message in case of authentication failure


    }

}
