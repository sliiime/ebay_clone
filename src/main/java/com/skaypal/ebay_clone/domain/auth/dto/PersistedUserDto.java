package com.skaypal.ebay_clone.domain.auth.dto;

import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static com.skaypal.ebay_clone.domain.user.UserRegStatus.*;

public class PersistedUserDto implements UserDetails {

    private User user;

    public PersistedUserDto(User user){
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<Role> roles = user.getRoles();

        List<GrantedAuthority> grantedAuthorities = roles.stream().map((role -> new SimpleGrantedAuthority(role.getRole()))).collect(Collectors.toList());

        return grantedAuthorities;
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return user.getRegistrationStatus() == ACCEPTED;
    }
}
