package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ValueOrderValidator implements ConstraintValidator<EndDateValidation, CreateItemDto> {



    @Override
    public void initialize(EndDateValidation valueOrderValidation) { }

    @Override
    public boolean isValid(CreateItemDto createItemDto, ConstraintValidatorContext ctx){
        Date smallDate = createItemDto.getStartDate();
        Date bigDate   = createItemDto.getEndDate();

        smallDate = smallDate == null ? new Date() : smallDate;

        return smallDate.compareTo(bigDate) == -1;

    }
}
