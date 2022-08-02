package com.skaypal.ebay_clone.domain.message.repository;

import com.skaypal.ebay_clone.domain.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> { }
