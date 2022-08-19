package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    public List<Rating> findAll();
    public Optional<Rating> findById(Integer id);

    public Rating save(Rating rating);
}