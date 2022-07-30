package com.skaypal.ebay_clone.domain.user.repositories;

import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    public Optional<User> findByAfm(String afm);
    public Optional<User> findByPhone(String phone);

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);
}
