package com.skaypal.ebay_clone.domain.interaction.repository;


import com.skaypal.ebay_clone.domain.interaction.model.Interaction;

public interface InteractionRepository {
    public Interaction save(Interaction interaction);
}
