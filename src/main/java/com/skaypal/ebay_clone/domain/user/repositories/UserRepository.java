package com.skaypal.ebay_clone.domain.user.repositories;

import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public Optional<User> findByAfm(String afm);
    public Optional<User> findByPhone(String phone);

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Integer id);

    public List<User> findAll();

    public User save(User user);

    public void delete(User user);

    public Page<User> getTransactionParticipants(User user, PageRequest pageRequest);
}
