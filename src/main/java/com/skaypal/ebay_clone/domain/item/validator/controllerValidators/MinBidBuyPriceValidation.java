package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinBidBuyPriceValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinBidBuyPriceValidation {


    String message() default "Buy price must be higher than minimum bid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String buyPrice();
    String minBid();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        MinBidBuyPriceValidation[] value();
    }

}
