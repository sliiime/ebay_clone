package com.skaypal.ebay_clone.domain.recommendation.repository;


import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.recommendation.dto.RecommendationDto;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RecommendationRepositoryImpl implements RecommendationRepository {

    private static final int RECOMMENDATIONS_PAGE_SIZE = 4;
    private final JPARecommendationRepository jpaRecommendationRepository;

    @Autowired
    public RecommendationRepositoryImpl(JPARecommendationRepository jpaRecommendationRepository){
            this.jpaRecommendationRepository = jpaRecommendationRepository;
    }
    @Override
    public Page<Recommendation> getUsersRecommendations(User user,int page) {


            return jpaRecommendationRepository.findRecommendationsByUser(user, PageRequest.of(page,RECOMMENDATIONS_PAGE_SIZE));
    }

    @Override
    public Recommendation saveOrUpdate(Recommendation recommendation){

        User user = recommendation.getUser();
        Item item = recommendation.getItem();

        Optional<Recommendation> optionalRecommendation = jpaRecommendationRepository.findRecommendationByUserAndItem(user,item);

        if (optionalRecommendation.isPresent()){
            Recommendation persistedRecommendation = optionalRecommendation.get();
            persistedRecommendation.update(recommendation);

            return jpaRecommendationRepository.save(persistedRecommendation);
        }else return jpaRecommendationRepository.save(recommendation);
    }
}
