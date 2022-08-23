package com.skaypal.ebay_clone.domain.session.service;

import com.skaypal.ebay_clone.domain.session.model.LoginForm;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username){
         Optional<User> user = userRepository.findByUsername(username);

         return new LoginForm(user.orElseThrow(() -> new UsernameNotFoundException(username)));
    }

}
