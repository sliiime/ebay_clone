package com.skaypal.ebay_clone.domain.item.validator.steps;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class UpdatedEndDateValidation extends ValidationStep<UpdateItemDto> {

    private ItemRepository itemRepository;
    public UpdatedEndDateValidation(ItemRepository itemRepository) {this.itemRepository = itemRepository;}

    @Override
    public ValidationResult validate(UpdateItemDto toValidate) {

        return itemRepository.getStartDate(toValidate.getId()).compareTo(toValidate.getEndDate()) < 0 ?
                checkNext(toValidate) :
                ValidationResult.invalid("Start date has to be before end date");
    }
}
