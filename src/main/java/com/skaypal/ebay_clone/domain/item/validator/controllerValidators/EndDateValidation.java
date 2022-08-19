package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueOrderValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EndDateValidation {

    String message() default "Invalid End Date";

    String startDate();

    String endDate();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        EndDateValidation[] value();
    }
}
