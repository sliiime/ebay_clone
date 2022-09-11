package com.skaypal.ebay_clone.domain.item.validator.steps;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class UpdatedStartDateValidation extends ValidationStep<UpdateItemDto> {


    private ItemRepository itemRepository;

    public UpdatedStartDateValidation(ItemRepository itemRepository) { this.itemRepository = itemRepository;}

    @Override
    public ValidationResult validate(UpdateItemDto toValidate){
        return itemRepository.getEndDate(toValidate.getId()).compareTo(toValidate.getStartDate()) > 0 ?
                checkNext(toValidate) :
                ValidationResult.invalid("End date has to be after start date");
    }
}
