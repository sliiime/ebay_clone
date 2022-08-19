package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueOrderValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinBidBuyPriceValidation {


    String message() default "Buy price must be higher than minimum bid";
    String buyPrice();
    String minBid();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        MinBidBuyPriceValidation[] value();
    }

}
