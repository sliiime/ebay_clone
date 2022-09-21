package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.model.ItemFields;
import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateItemDtoValidator implements ConstraintValidator<UpdateItemDtoValidation, UpdateItemDto> {
    @Override
    public void initialize(UpdateItemDtoValidation validation){}

    @Override
    public boolean isValid(UpdateItemDto updateItemDto, ConstraintValidatorContext ctx){
        if (updateItemDto.getToUpdate() == null) throw new BadRequestException("Item Update Request contains no fields to be updated");
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
                    if (updateItemDto.getLatitude() == null ) return false;
                    if (updateItemDto.getLongitude() == null) return false;
                    break;
                case LONGITUDE:
                    if (updateItemDto.getLongitude() == null) return false;
                    if (updateItemDto.getLatitude() == null ) return false;
                    break;
                case START_DATE:
                    if (updateItemDto.getStartDate() == null) return false;
                    break;
                case CATEGORIES:
                    if (updateItemDto.getCategories() == null || updateItemDto.getCategories().size() == 0) return false;
            }
        }
        return true;
    }
}
