package com.skaypal.ebay_clone.domain.bid.validator;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class BidIsHigherThanMinBid extends ValidationStep<CreateBidDto> {

    private ItemValidator itemValidator;
    public BidIsHigherThanMinBid(ItemValidator itemValidator) {
        this.itemValidator = itemValidator;
    }

    @Override
    public ValidationResult validate(CreateBidDto toValidate) {
        return itemValidator.isHigherThanMinBid(toValidate.getItemId(),toValidate.getPrice()) ?
               checkNext(toValidate) :
               ValidationResult.invalid("Current bid is lower than the minimum bid");
    }
}
