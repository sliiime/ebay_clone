package com.skaypal.ebay_clone.domain.interaction.service;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.interaction.model.InteractionStatus;
import com.skaypal.ebay_clone.domain.interaction.repository.InteractionRepository;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class InteractionService {


    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository){
            this.interactionRepository = interactionRepository;
    }

    public static void initializeInteractions(Page<ViewItemDto> viewItemDtoPage) {

    }


    public Interaction saveInteraction(Integer userId, Integer itemId, InteractionStatus interactionStatus){

        User user = new User(userId);
        Item item = new Item(itemId);
        Interaction interaction = new Interaction(user,item,interactionStatus);

        return interactionRepository.save(interaction);

    }
}
