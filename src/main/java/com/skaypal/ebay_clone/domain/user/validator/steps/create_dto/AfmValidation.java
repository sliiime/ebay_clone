package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class AfmValidation extends ValidationStep<CreateUserDto> {

    JPAUserRepository JPAUserRepository;

    public AfmValidation(JPAUserRepository JPAUserRepository){
        this.JPAUserRepository = JPAUserRepository;
    }
    @Override
    public ValidationResult validate(CreateUserDto toValidate) {
            return JPAUserRepository.findByAfm(toValidate.getAfm()).isPresent() ?
                    ValidationResult.invalid(String.format("User with AFM [%s] already exists",toValidate.getAfm())) :
                    checkNext(toValidate);
    }
}
