package com.skaypal.ebay_clone.domain.recommendation.repository;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.recommendation.model.Recommendation;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPARecommendationRepository extends JpaRepository<Recommendation,Integer> {

    Page<Recommendation> findRecommendationsByUser(User user, Pageable pageable);

    Optional<Recommendation> findRecommendationByUserAndItem(User user, Item item);
}
