package com.skaypal.ebay_clone.domain.rating.service;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.dto.ViewRatingDto;
import com.skaypal.ebay_clone.domain.rating.exceptions.RatingBadRequestException;
import com.skaypal.ebay_clone.domain.rating.exceptions.RatingNotFoundException;
import com.skaypal.ebay_clone.domain.rating.model.Rating;
import com.skaypal.ebay_clone.domain.rating.repositories.JPARatingRepository;
import com.skaypal.ebay_clone.domain.rating.repositories.RatingRepository;
import com.skaypal.ebay_clone.domain.rating.validator.RatingValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private RatingRepository ratingRepository;
    RatingValidator ratingValidator;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RatingValidator ratingValidator){
        this.ratingRepository = ratingRepository;
        this.ratingValidator = ratingValidator;
    }

    public List<ViewRatingDto> getRatings(){
        return ratingRepository.findAll().stream().map((r) -> new ViewRatingDto(r)).collect(Collectors.toList());
    }


    public ViewRatingDto getRating(Integer id) {
        return new ViewRatingDto(ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException(id)));
    }

    public ViewRatingDto createRating(CreateRatingDto createRatingDto) {
        //Validate createRatingDto
        ValidationResult validationResult = ratingValidator.validateCreateRatingDto(createRatingDto);

        if (!validationResult.isValid()) throw new RatingBadRequestException(validationResult.getErrorMessage());

        Rating rating = new Rating(createRatingDto);
        return new ViewRatingDto(ratingRepository.save(rating));
    }
}
