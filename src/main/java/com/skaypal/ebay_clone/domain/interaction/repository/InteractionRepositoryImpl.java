package com.skaypal.ebay_clone.domain.interaction.repository;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class InteractionRepositoryImpl implements InteractionRepository{


    private final JpaInteractionRepository jpaInteractionRepository;

    @Autowired
    public InteractionRepositoryImpl(JpaInteractionRepository jpaInteractionRepository){
        this.jpaInteractionRepository = jpaInteractionRepository;
    }

    public Interaction save(Interaction interaction){
        return jpaInteractionRepository.save(interaction);
    }

    public Optional<Interaction> findInteractionByUserAndItem(User user, Item item){
        return jpaInteractionRepository.findInteractionByUserAndItem(user,item);
    }

    @Override
    public List<Interaction> findUsersInteractions(User user) {
        return jpaInteractionRepository.findInteractionsByUserOrderByItem(user);
    }

    @Override
    public List<Integer> getUserIds(){
        return jpaInteractionRepository.getUserIds();
    }

}
