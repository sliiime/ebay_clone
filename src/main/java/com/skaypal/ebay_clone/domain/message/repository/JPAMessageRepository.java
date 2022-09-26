package com.skaypal.ebay_clone.domain.message.repository;

import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAMessageRepository extends JpaRepository<Message,Integer> {

    @Query("SELECT distinct u.id  FROM User u WHERE u.id in " +
            "(SELECT m.receiver.id FROM Message m WHERE m.sender = ?1)" +
            "or u.id in " +
            "(SELECT m.sender.id FROM Message m WHERE m.receiver =?1)"

    )
    public Page<Integer> getConversationUsers(User ofUser, PageRequest pageRequest);
}
