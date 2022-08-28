package com.skaypal.ebay_clone.domain.role.repository;

import com.skaypal.ebay_clone.domain.country.repository.JPACountryRepository;
import com.skaypal.ebay_clone.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JPARoleRepository extends JpaRepository<Role,Integer>{
    Optional<Role> getByRole(String role);

}
