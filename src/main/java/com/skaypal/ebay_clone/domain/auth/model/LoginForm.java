package com.skaypal.ebay_clone.domain.auth.model;

import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class LoginForm implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public LoginForm(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new UserNotFoundException(String.format("User with username [%s] does not exist",username));

        User user = userOptional.get();

        List<SimpleGrantedAuthority> grantedAuthorities = user.getRoles().stream().
                map((role -> new SimpleGrantedAuthority(role.getRole()))).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuthorities);




    }
}
