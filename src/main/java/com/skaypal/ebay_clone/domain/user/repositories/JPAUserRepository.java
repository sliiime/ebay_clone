package com.skaypal.ebay_clone.domain.user.repositories;

import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User,Integer> {


    public Optional<User> findByAfm(String afm);

    public Optional<User> findByPhone(String phone);

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    @Query(
            "SELECT u FROM User u WHERE u.id IN " +
                    "(SELECT u.id FROM Item i, User u WHERE i.seller.id = ?1 AND i.boughtBy.id = u.id) " +
                    "OR u.id IN " +
                    "(SELECT u.id FROM Item i, User u WHERE i.boughtBy.id = ?1 AND u.id = i.seller.id)")
    public List<User> getTransactionPartners(User user);
}

