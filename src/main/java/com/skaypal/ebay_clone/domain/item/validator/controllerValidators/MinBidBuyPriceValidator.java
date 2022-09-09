package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ValidatableItemDto;
import org.hibernate.validator.internal.metadata.facets.Validatable;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinBidBuyPriceValidator implements ConstraintValidator<MinBidBuyPriceValidation, ValidatableItemDto> {

    private String minBid;
    private String buyPrice;

    @Override
    public void initialize(MinBidBuyPriceValidation minBidBuyPriceValidation){
    }

    @Override
    public boolean isValid(ValidatableItemDto itemDto, ConstraintValidatorContext ctx){
        Float minBid = itemDto.getMinBid();
        Float buyPrice = itemDto.getBuyPrice();

        return minBid != null && buyPrice != null ? minBid <= buyPrice : true;
    }
}
