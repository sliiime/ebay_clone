package com.skaypal.ebay_clone.domain.message.repository;

import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    @Override
    public Page<Message> getConversationMessages(User user1,User user2,PageRequest pageRequest){
        return jpaMessageRepository.getConversationMessages(user1,user2,pageRequest);
    }
}
