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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.skaypal.ebay_clone.domain.interaction.model.InteractionStatus.IGNORED;
import static com.skaypal.ebay_clone.domain.interaction.model.InteractionStatus.VIEWED;

@Service
public class InteractionService {


    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository){
            this.interactionRepository = interactionRepository;
    }

    public void initializeInteractions(Page<ViewItemDto> viewItemDtoPage,int userId) {

        User user = new User(userId);

        List<Item> items = viewItemDtoPage.stream().map((viewItemDto) -> new Item(viewItemDto.getId())).collect(Collectors.toList());

        for (Item item : items){
            Optional<Interaction> interactionOptional = interactionRepository.findInteractionByUserAndItem(user,item);

            Interaction interaction;

            if (interactionOptional.isEmpty()) {
                interaction = new Interaction(user, item, IGNORED);
                interactionRepository.save(interaction);
            }
        }


    }


    public Interaction itemViewed(Integer userId, Integer itemId){

        User user = new User(userId);
        Item item = new Item(itemId);

        Optional<Interaction> optionalInteraction = interactionRepository.findInteractionByUserAndItem(user,item);

        Interaction interaction = new Interaction(user,item,VIEWED);


        if (optionalInteraction.isPresent()){

            Interaction persistedInteraction = optionalInteraction.get();

            if (persistedInteraction.getInteractionStatus() == IGNORED){
                persistedInteraction.setInteractionStatus(VIEWED);
                interaction = persistedInteraction;
            }
        }

        return interactionRepository.save(interaction);

    }
    public static double convertInteractionStatusToDouble(InteractionStatus interactionStatus){
        return interactionStatus == VIEWED ? 2 : interactionStatus == IGNORED ? 1 : 0;
    }
}
