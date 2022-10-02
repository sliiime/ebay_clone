package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;
import com.skaypal.ebay_clone.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    public List<Rating> findAll();
    public Optional<Rating> findById(Integer id);

    public int ratingsOf(Integer user1, Integer user2);
    public Rating save(Rating rating);

    Optional<Rating> findByRatedByAndRated(User rater, User rated);
}
