package com.skaypal.ebay_clone.domain.role.service;

import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleService {

    RoleRepository roleRepository;


    @Autowired
    public RoleService (RoleRepository roleRepository){
            this.roleRepository = roleRepository;
    }

    public Optional<Role> getRole(String role){
        return roleRepository.getRole(role);
    }

}
