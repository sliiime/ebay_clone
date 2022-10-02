package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JPARatingRepository extends JpaRepository<Rating,Integer>{

    @Query("SELECT count(r) FROM Rating r WHERE r.rated.id = ?1 AND r.ratedBy.id = ?2")
    public int ratingsOf(Integer user1,Integer user2);

    public Optional<Rating> findRatingByRatedByAndRated(User ratedBy,User rated);
}
