package com.skaypal.ebay_clone.domain.interaction.repository;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaInteractionRepository extends JpaRepository<Interaction, Integer> {
    public Optional<Interaction> findInteractionByUserAndItem(User user, Item item);
}
