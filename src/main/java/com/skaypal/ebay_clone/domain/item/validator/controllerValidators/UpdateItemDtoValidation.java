package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UpdateItemDtoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateItemDtoValidation {

    String message() default "Invalid Update Request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        UpdateItemDtoValidation[] value();
    }
}
