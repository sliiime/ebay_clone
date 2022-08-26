package com.skaypal.ebay_clone.domain.role.repository;

import com.skaypal.ebay_clone.domain.role.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    public Optional<Role> getRole(String Role);
    public Optional<Role> findById(Integer id);
    public List<Role> findAll();
}
