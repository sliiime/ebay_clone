package com.skaypal.ebay_clone.domain.item.validator.steps;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class UpdatedMinBidValidation extends ValidationStep<UpdateItemDto> {

    private ItemRepository itemRepository;
    public UpdatedMinBidValidation(ItemRepository itemRepository) {this.itemRepository = itemRepository;}

    @Override
    public ValidationResult validate(UpdateItemDto toValidate){
        return itemRepository.getBuyoutPrice(toValidate.getId()) >= toValidate.getMinBid() ?
                checkNext(toValidate) :
                ValidationResult.invalid("Buyout price cannot be lower than the minimum bid");
    }
}
