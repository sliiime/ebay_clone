package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RatingRepositoryImpl implements RatingRepository{

    JPARatingRepository jpaRatingRepository;

    @Autowired
    public RatingRepositoryImpl(JPARatingRepository jpaRatingRepository){
        this.jpaRatingRepository = jpaRatingRepository;
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        return jpaRatingRepository.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return jpaRatingRepository.findAll();
    }

    public Rating save(Rating rating){
        return jpaRatingRepository.save(rating);
    }
}
