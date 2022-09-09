package com.skaypal.ebay_clone.domain.item.validator.controllerValidators;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ValidatableItemDto;
import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ValueOrderValidator implements ConstraintValidator<EndDateValidation, ValidatableItemDto> {



    @Override
    public void initialize(EndDateValidation valueOrderValidation) { }

    @Override
    public boolean isValid(ValidatableItemDto itemDto, ConstraintValidatorContext ctx){
        Date smallDate = itemDto.getStartDate();
        Date bigDate = itemDto.getEndDate();
        
        if (bigDate == null || smallDate == null) return true;

        return smallDate.compareTo(bigDate) == -1;

    }
}
