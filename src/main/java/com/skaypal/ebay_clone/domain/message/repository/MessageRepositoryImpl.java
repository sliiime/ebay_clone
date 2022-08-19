package com.skaypal.ebay_clone.domain.message.repository;

import com.skaypal.ebay_clone.domain.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository{

    JPAMessageRepository jpaMessageRepository;

    @Autowired
    public MessageRepositoryImpl(JPAMessageRepository jpaMessageRepository){
        this.jpaMessageRepository = jpaMessageRepository;
    }

    @Override
    public List<Message> findAll() {
        return this.jpaMessageRepository.findAll();
    }

    @Override
    public Message save(Message message){
        return this.jpaMessageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(Integer id){
        return jpaMessageRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id){
        jpaMessageRepository.deleteById(id);
    }
}
