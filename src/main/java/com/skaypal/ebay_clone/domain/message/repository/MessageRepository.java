package com.skaypal.ebay_clone.domain.message.repository;

import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    public Optional<Message> findById(Integer id);
    public List<Message> findAll();

    public Message save(Message message);

    public void deleteById(Integer id);

    Page<Integer> getConversationUsers(User ofUser, PageRequest pageRequest);
}
