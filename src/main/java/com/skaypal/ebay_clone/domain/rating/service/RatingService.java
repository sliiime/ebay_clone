package com.skaypal.ebay_clone.domain.rating.service;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.dto.ViewRatingDto;
import com.skaypal.ebay_clone.domain.rating.exceptions.RatingNotFoundException;
import com.skaypal.ebay_clone.domain.rating.model.Rating;
import com.skaypal.ebay_clone.domain.rating.repositories.RatingRepository;
import com.skaypal.ebay_clone.domain.rating.validator.RatingValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RatingService {

    private RatingRepository ratingRepository;
    @Autowired
    RatingValidator ratingValidator;

    @Autowired
    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<ViewRatingDto> getRatings(){
        return ratingRepository.findAll().stream().map((r) -> new ViewRatingDto(r)).collect(Collectors.toList());
    }


    public ViewRatingDto getRating(Integer id) {
        return new ViewRatingDto(ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException(id)));
    }

    public ViewRatingDto createRating(CreateRatingDto createRatingDto) {
        //Validate createRatingDto
        ratingValidator.validateCreateRatingDto(createRatingDto);

        Rating rating = new Rating(createRatingDto);
        return new ViewRatingDto(ratingRepository.save(rating));
    }
}
