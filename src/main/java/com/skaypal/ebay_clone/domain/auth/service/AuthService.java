package com.skaypal.ebay_clone.domain.auth.service;

import com.skaypal.ebay_clone.domain.auth.dto.AuthenticationResult;
import com.skaypal.ebay_clone.domain.auth.dto.LoginFormDto;
import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import com.skaypal.ebay_clone.utils.validator.ServiceResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    JWTUtil jwtUtil;
    UserRepository userRepository;
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(JWTUtil jwtUtil,
                       UserRepository userRepository,
                       AuthenticationManager authenticationManager) {

        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResult<HashMap<String, Object>> authenticate(LoginFormDto loginFormDto) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginFormDto.getUsername(),
                loginFormDto.getPassword());

        authenticationManager.authenticate(authInputToken);
        Optional<User> user = userRepository.findByUsername(loginFormDto.getUsername());

        HashMap<String, Object> map = new HashMap<>();

        AuthenticationResult<HashMap<String, Object>> result;

        if (user.isPresent()) {
            User u = user.get();
            map.put("token", jwtUtil.generateToken(u));
            for (Role role : u.getRoles()) {
                switch (role.getRole()) {
                    case "ADMIN": {
                        map.put("status", "admin");
                        break;
                    }
                    case "UNAUTHORIZED_USER": {
                        map.put("status", "pending");
                        break;
                    }
                    case "AUTHORIZED_USER" : {
                        map.put("status","approved");
                        break;
                    }
                    default:
                    break;
                }
                break;
            }
            result = AuthenticationResult.of(map);
        } else result = AuthenticationResult.of(ServiceResultStatus.NOT_FOUND);

        return result;

    }
}
