package com.skaypal.ebay_clone.domain.item.validator.steps;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

import java.util.Optional;

public class ItemIsEligibleForUpdate extends ValidationStep<UpdateItemDto> {

    private final ItemRepository itemRepository;
    public ItemIsEligibleForUpdate(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ValidationResult validate(UpdateItemDto toValidate){

            Integer totalBids = itemRepository.getNumOfBids(toValidate.getId());

            return totalBids == 0 ?
                    checkNext(toValidate) :
                    ValidationResult.invalid("Cannot update an item which is not in preview mode or has already received bids");

    }
}
