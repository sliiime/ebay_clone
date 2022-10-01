package com.skaypal.ebay_clone.domain.recommendation.repository;

import com.skaypal.ebay_clone.domain.recommendation.dto.RecommendationDto;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;

public interface RecommendationRepository {


    Page<Recommendation> getUsersRecommendations(User user,int page);
}
