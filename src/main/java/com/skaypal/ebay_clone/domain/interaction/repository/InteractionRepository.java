package com.skaypal.ebay_clone.domain.interaction.repository;


import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface InteractionRepository {
    public Interaction save(Interaction interaction);

    public Optional<Interaction> findInteractionByUserAndItem(User user, Item item);
    public List<Interaction> findUsersInteractions(User user);


    public List<Integer> getUserIds();

}
