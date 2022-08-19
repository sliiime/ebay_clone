package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ValueOrderValidator implements ConstraintValidator<EndDateValidation,Date> {

    private String startDate;
    private String endDate;

    @Override
    public void initialize(EndDateValidation valueOrderValidation) {
        this.startDate = valueOrderValidation.startDate();
        this.endDate = valueOrderValidation.endDate();
    }

    @Override
    public boolean isValid(Date d, ConstraintValidatorContext ctx){
        Date smallDate = (Date) new BeanWrapperImpl(d).getPropertyValue(startDate);
        Date bigDate   = (Date) new BeanWrapperImpl(d).getPropertyValue(endDate);

        return smallDate.compareTo(bigDate) == -1;

    }
}
