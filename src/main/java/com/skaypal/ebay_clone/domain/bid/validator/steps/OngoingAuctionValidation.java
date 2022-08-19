package com.skaypal.ebay_clone.domain.bid.validator.steps;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class OngoingAuctionValidation extends ValidationStep<CreateBidDto> {

    ItemValidator itemValidator;

    public OngoingAuctionValidation(ItemValidator itemValidator){
            this.itemValidator = itemValidator;
    }

    @Override
    public ValidationResult validate(CreateBidDto toValidate) {
        return itemValidator.auctionIsEligibleForBids(toValidate.getItemId()) ?
                checkNext(toValidate) :
                ValidationResult.invalid("Bidding window has expired");

    }
}
