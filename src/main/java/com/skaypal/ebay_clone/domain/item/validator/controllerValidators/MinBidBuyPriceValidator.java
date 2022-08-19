package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinBidBuyPriceValidator implements ConstraintValidator<MinBidBuyPriceValidation,Float> {

    private String minBid;
    private String buyPrice;

    @Override
    public void initialize(MinBidBuyPriceValidation minBidBuyPriceValidation){
        this.minBid = minBidBuyPriceValidation.minBid();
        this.buyPrice = minBidBuyPriceValidation.buyPrice();
    }

    @Override
    public boolean isValid(Float f, ConstraintValidatorContext ctx){
        Float buyPrice = (Float) new BeanWrapperImpl(f).getPropertyValue(this.buyPrice);
        Float minBid =  (Float) new BeanWrapperImpl(f).getPropertyValue(this.minBid);

        return minBid <= buyPrice;
    }
}
