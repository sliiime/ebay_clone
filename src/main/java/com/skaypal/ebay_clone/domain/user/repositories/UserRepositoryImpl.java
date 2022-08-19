package com.skaypal.ebay_clone.domain.user.repositories;


import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private JPAUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImpl(JPAUserRepository jpaRepository){
        this.jpaUserRepository = jpaRepository;
    }

    @Override
    public Optional<User> findByAfm(String afm) {
        return jpaUserRepository.findByAfm(afm);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return jpaUserRepository.findByPhone(phone);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Integer id){
        return jpaUserRepository.findById(id);
    }

    @Override
    public List<User> findAll(){
        return jpaUserRepository.findAll();
    }

    public User save(User user){
        return jpaUserRepository.save(user);
    }

    public void delete(User user){
        jpaUserRepository.delete(user);
    }
}
