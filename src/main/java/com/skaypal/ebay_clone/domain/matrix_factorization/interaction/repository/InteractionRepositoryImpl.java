package com.skaypal.ebay_clone.domain.matrix_factorization.interaction.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class InteractionRepositoryImpl implements InteractionRepository{


    private final JpaInteractionRepository jpaInteractionRepository;

    @Autowired
    public InteractionRepositoryImpl(JpaInteractionRepository jpaInteractionRepository){
        this.jpaInteractionRepository = jpaInteractionRepository;
    }

}
