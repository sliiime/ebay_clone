package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.model.ItemFields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateItemDtoValidator implements ConstraintValidator<UpdateItemDtoValidation, UpdateItemDto> {
    @Override
    public void initialize(UpdateItemDtoValidation validation){}

    @Override
    public boolean isValid(UpdateItemDto updateItemDto, ConstraintValidatorContext ctx){
        for (ItemFields field : updateItemDto.getToUpdate()) {
            switch (field) {
                case NAME:
                    if (updateItemDto.getName() == null) return false;
                    break;
                case BUY_PRICE:
                    if (updateItemDto.getBuyPrice() == null) return false;
                    break;
                case DESCRIPTION:
                    break;
                case MIN_BID:
                    if (updateItemDto.getMinBid() == null) return false;
                    break;
                case END_DATE:
                    if (updateItemDto.getEndDate() == null) return false;
                    break;
                case LATITUDE:
                    break;
                case LONGITUDE:
                    break;
                case START_DATE:
                    if (updateItemDto.getStartDate() == null) return false;
                    break;
            }
        }
        return true;
    }
}
