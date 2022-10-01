package com.skaypal.ebay_clone.domain.recommendation.repository;


import com.skaypal.ebay_clone.domain.recommendation.dto.RecommendationDto;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

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
}
