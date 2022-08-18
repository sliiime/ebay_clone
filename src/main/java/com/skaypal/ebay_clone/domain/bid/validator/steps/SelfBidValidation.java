package com.skaypal.ebay_clone.domain.bid.validator.steps;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class SelfBidValidation extends ValidationStep<CreateBidDto> {

    ItemValidator itemValidator;

    public SelfBidValidation(ItemValidator itemValidator){
            this.itemValidator = itemValidator;
    }

    @Override
    public ValidationResult validate(CreateBidDto toValidate) {
        return !itemValidator.isSellerOfItem(toValidate.getItemId(), toValidate.getBidderId())
                ? checkNext(toValidate)
                : ValidationResult.invalid("You can not submit a bid for your own item");
    }
}
