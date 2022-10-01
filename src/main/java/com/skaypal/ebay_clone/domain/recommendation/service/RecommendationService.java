package com.skaypal.ebay_clone.domain.recommendation.service;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.interaction.repository.InteractionRepository;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.domain.recommendation.dto.RecommendationDto;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.recommendation.repository.RecommendationRepository;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.skaypal.ebay_clone.domain.interaction.model.InteractionStatus.VIEWED;
import static com.skaypal.ebay_clone.domain.interaction.service.InteractionService.convertInteractionStatusToDouble;

@Service
public class RecommendationService {

    private final int RECOMMENDATION_SAMPLE_SIZE = 30;
    private final RecommendationRepository recommendationRepository;

    private final InteractionRepository interactionRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository,
                                 InteractionRepository interactionRepository,
                                 ItemRepository itemRepository){

        this.recommendationRepository = recommendationRepository;
        this.interactionRepository = interactionRepository;
        this.itemRepository = itemRepository;
    }

    public Page<RecommendationDto> getRecommendations(int userId){
            generateRecommendations(userId);

            User user = new User(userId);

            Page<Recommendation> recommendationPage = recommendationRepository.getUsersRecommendations(user,0);

            return recommendationPage.map(RecommendationDto::new);

    }

    private void generateRecommendations(int forUser) {

        List<Integer> userIds = interactionRepository.getUserIds();
        int totalUserIds = userIds.size();

        int totalPicks = RECOMMENDATION_SAMPLE_SIZE > totalUserIds ? totalUserIds : RECOMMENDATION_SAMPLE_SIZE;

        HashMap<Integer,Integer> userToCell = new HashMap<>();
        HashMap<Integer,Integer> cellToUser = new HashMap<>();

        Map<Integer,Integer> itemToCell = new HashMap<>();
        Map<Integer,Integer> cellToItem = new HashMap<>();

        int totalItems = itemRepository.getTotalItemsCount();

        double[][] interactionMatrix = new double[totalUserIds][totalItems];


        User user = new User(forUser);

        List<Interaction> forUserInteractions = interactionRepository.findUsersInteractions(user);


        int currentUser = forUser;

        userToCell.put(forUser,0);
        cellToUser.put(0,forUser);



        Random random = new Random(new Date().getTime());

        Set<Integer> pickedUsers = new HashSet<>();
        pickedUsers.add(forUser);

        int itemAvailablePos = 0;
        int userAvailablePos = 0;





        for (Interaction interaction : forUserInteractions) {


            int itemId = interaction.getItem().getId();

            Integer itemCellPos = itemToCell.get(itemId);

            if (itemCellPos == null) {
                itemToCell.put(itemId, itemAvailablePos);
                cellToItem.put(itemAvailablePos, itemId);
                itemCellPos = itemAvailablePos++;
            }

            currentUser = interaction.getUser().getId();

            interactionMatrix[currentUser][itemCellPos] = convertInteractionStatusToDouble(interaction.getInteractionStatus());

        }

        for (int i = 0 ; i < totalPicks; i++){

            int pick = random.nextInt() % totalUserIds;

            Integer userId = userIds.get(pick);

            pickedUsers.add(userId);

            if (pickedUsers.contains(userId)){
                i--;
                continue;
            }

            user = new User(userId);

            List<Interaction> usersInteractions = interactionRepository.findUsersInteractions(user);



            for (Interaction interaction : usersInteractions) {

                int itemId = interaction.getItem().getId();

                Integer cellPos = itemToCell.get(itemId);

                if (cellPos == null) {
                    itemToCell.put(itemId, itemAvailablePos);
                    cellToItem.put(itemAvailablePos, itemId);
                    cellPos = itemAvailablePos++;
                }

                currentUser = interaction.getUser().getId();
                interactionMatrix[currentUser][cellPos] = convertInteractionStatusToDouble(interaction.getInteractionStatus());

            }

        }
    }


}
