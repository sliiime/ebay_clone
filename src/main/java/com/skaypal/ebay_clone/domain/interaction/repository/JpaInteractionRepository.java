package com.skaypal.ebay_clone.domain.interaction.repository;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaInteractionRepository extends JpaRepository<Interaction, Integer> {
    public Optional<Interaction> findInteractionByUserAndItem(User user, Item item);

    public List<Interaction> findInteractionsByUserOrderByItem(User user);

    public Page<Interaction> findAll(Pageable pageable);


    @Query("SELECT distinct i.user.id FROM Interaction i")
    public List<Integer> getUserIds();
}
