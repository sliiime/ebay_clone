package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JPARatingRepository extends JpaRepository<Rating,Integer>{

    @Query("SELECT count(r) FROM Rating r WHERE r.rated.id = ?1 AND r.ratedBy.id = ?2")
    public int ratingsOf(Integer user1,Integer user2);
}
