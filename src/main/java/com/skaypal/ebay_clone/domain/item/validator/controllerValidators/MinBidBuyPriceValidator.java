package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinBidBuyPriceValidator implements ConstraintValidator<MinBidBuyPriceValidation, CreateItemDto> {

    private String minBid;
    private String buyPrice;

    @Override
    public void initialize(MinBidBuyPriceValidation minBidBuyPriceValidation){
    }

    @Override
    public boolean isValid(CreateItemDto createItemDto, ConstraintValidatorContext ctx){
        Float minBid = createItemDto.getMinBid();
        Float buyPrice = createItemDto.getBuyPrice();

        return minBid != null && buyPrice != null ? minBid <= buyPrice : false;
    }
}
