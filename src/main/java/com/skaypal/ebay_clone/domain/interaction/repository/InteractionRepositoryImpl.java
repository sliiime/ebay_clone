package com.skaypal.ebay_clone.domain.interaction.repository;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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

}
