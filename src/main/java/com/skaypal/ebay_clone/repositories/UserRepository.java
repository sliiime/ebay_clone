package com.skaypal.ebay_clone.repositories;

import com.skaypal.ebay_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
