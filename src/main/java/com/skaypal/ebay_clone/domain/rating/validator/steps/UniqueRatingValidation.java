package com.skaypal.ebay_clone.domain.rating.validator.steps;

import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.repositories.RatingRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class UniqueRatingValidation extends ValidationStep<CreateRatingDto> {

    RatingRepository ratingRepository;
    public UniqueRatingValidation(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public ValidationResult validate(CreateRatingDto toValidate){
                return ratingRepository.ratingsOf(toValidate.getRatedId(),toValidate.getRatedById()) == 0 ?
                        checkNext(toValidate) :
                        ValidationResult.invalid("You have already rated this user");
    }
}
