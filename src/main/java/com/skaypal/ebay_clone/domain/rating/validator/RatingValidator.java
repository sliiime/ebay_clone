package com.skaypal.ebay_clone.domain.rating.validator;

import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.repositories.RatingRepository;
import com.skaypal.ebay_clone.domain.rating.validator.steps.HaveMadeTransaction;
import com.skaypal.ebay_clone.domain.rating.validator.steps.RatedByValidator;
import com.skaypal.ebay_clone.domain.rating.validator.steps.RatedValidator;
import com.skaypal.ebay_clone.domain.rating.validator.steps.UniqueRatingValidation;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingValidator {

    UserValidator userValidator;

    ItemValidator itemValidator;

    RatingRepository ratingRepository;

    @Autowired
    public RatingValidator(UserValidator userValidator,
                           ItemValidator itemValidator,
                           RatingRepository ratingRepository){

        this.userValidator = userValidator;
        this.itemValidator = itemValidator;
        this.ratingRepository = ratingRepository;
    }
    public ValidationResult validateCreateRatingDto(CreateRatingDto createRatingDto){

        return new RatedValidator(userValidator)
                .linkWith(new RatedByValidator(userValidator))
                .linkWith(new HaveMadeTransaction(itemValidator))
                .linkWith(new UniqueRatingValidation(ratingRepository))
                .validate(createRatingDto);

    }
}
