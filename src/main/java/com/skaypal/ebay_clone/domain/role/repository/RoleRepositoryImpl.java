package com.skaypal.ebay_clone.domain.role.repository;

import com.skaypal.ebay_clone.domain.role.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

    JPARoleRepository roleRepository;

    @Autowired
    public RoleRepositoryImpl(JPARoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(Integer id){
        return roleRepository.findById(id);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> getRole(String role){
        return roleRepository.getByRole(role);
    }
}
