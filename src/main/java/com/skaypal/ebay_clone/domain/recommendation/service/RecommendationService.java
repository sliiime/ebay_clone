package com.skaypal.ebay_clone.domain.recommendation.service;

import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.interaction.repository.InteractionRepository;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.domain.recommendation.dto.RecommendationDto;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.recommendation.model.RecommendationStatus;
import com.skaypal.ebay_clone.domain.recommendation.repository.RecommendationRepository;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.utils.matrix_factorization.MatrixFactorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.skaypal.ebay_clone.domain.interaction.service.InteractionService.interactionStatusToDouble;
import static com.skaypal.ebay_clone.domain.recommendation.model.RecommendationStatus.IGNORE;
import static com.skaypal.ebay_clone.domain.recommendation.model.RecommendationStatus.RECOMMEND;

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

        int itemAvailablePos = 0;
        int userAvailablePos = 0;

        int currentUser = forUser;

        userToCell.put(currentUser,userAvailablePos);
        cellToUser.put(userAvailablePos,currentUser);

        Set<Integer> pickedUsers = new HashSet<>();
        pickedUsers.add(forUser);


        Integer itemCellPos;
        Integer userCellPos = userAvailablePos++;


        //Creating row of user we want to generate recommendations for
        for (Interaction interaction : forUserInteractions) {

            int itemId = interaction.getItem().getId();

            itemCellPos = itemToCell.get(itemId);

            if (itemCellPos == null) {
                itemToCell.put(itemId, itemAvailablePos);
                cellToItem.put(itemAvailablePos, itemId);
                itemCellPos = itemAvailablePos++;
            }

            interactionMatrix[userCellPos][itemCellPos] = interactionStatusToDouble(interaction.getInteractionStatus());

        }

        Random random = new Random(new Date().getTime());

        //Creating rows of sample users
        for (int i = 0 ; i < totalPicks - 1; i++){

            int pick = random.nextInt() % totalUserIds;

            Integer userId = userIds.get(pick);


            if (pickedUsers.contains(userId)){
                i--;
                continue;
            }else pickedUsers.add(userId);

            user = new User(userId);

            List<Interaction> usersInteractions = interactionRepository.findUsersInteractions(user);


            for (Interaction interaction : usersInteractions) {

                int itemId = interaction.getItem().getId();

                itemCellPos = itemToCell.get(itemId);

                if (itemCellPos == null) {
                    itemToCell.put(itemId, itemAvailablePos);
                    cellToItem.put(itemAvailablePos, itemId);
                    itemCellPos = itemAvailablePos++;
                }

                userCellPos = userToCell.get(userId);

                if (userCellPos == null){
                    userToCell.put(userId,userAvailablePos);
                    cellToUser.put(userAvailablePos,userId);
                    userCellPos = userAvailablePos++;
                }

                interactionMatrix[userCellPos][itemCellPos] = interactionStatusToDouble(interaction.getInteractionStatus());

            }

        }
        double[][] recommendationMatrix = MatrixFactorization.run(interactionMatrix,30000,0.0002,0.02);
        for (int i = 0 ; i < totalUserIds; i++){
            int userId = cellToUser.get(i);
            user = new User(userId);
            for (int j = 0; j < totalItems; j++){
                int itemId = cellToItem.get(j);
                Item item = new Item(itemId);

                RecommendationStatus recommendationStatus = doubleToRecommendationStatus(recommendationMatrix[userId][itemId]);

                Recommendation recommendation = new Recommendation(user,item,recommendationStatus);

                recommendationRepository.saveOrUpdate(recommendation);
            }
        }

    }

    private RecommendationStatus doubleToRecommendationStatus(double recommendationDouble) {
       return recommendationDouble < 1.5 ? IGNORE : RECOMMEND ;
    }


}
