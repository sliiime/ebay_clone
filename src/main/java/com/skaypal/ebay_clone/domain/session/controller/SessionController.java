package com.skaypal.ebay_clone.domain.session.controller;

import com.skaypal.ebay_clone.domain.session.dto.JWTResponse;
import com.skaypal.ebay_clone.domain.session.dto.LoginRequest;
import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;
import com.skaypal.ebay_clone.utils.jwt.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ebay_clone/api/login")
public class SessionController {


    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;

    JWTUtility jwtUtility;

    public static final String location = "ebay_clone/api/login";


    @Autowired
    public SessionController(AuthenticationManager authenticationManager,
                             UserDetailsService userDetailsService,
                             JWTUtility jwtUtility){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials",e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new JWTResponse(token));

    }


}
