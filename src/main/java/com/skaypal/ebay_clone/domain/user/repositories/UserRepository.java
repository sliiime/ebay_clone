package com.skaypal.ebay_clone.domain.user.repositories;

import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
