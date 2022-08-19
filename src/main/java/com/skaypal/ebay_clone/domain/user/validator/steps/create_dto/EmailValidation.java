package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class EmailValidation extends ValidationStep<CreateUserDto> {

    JPAUserRepository JPAUserRepository;

    public EmailValidation(JPAUserRepository JPAUserRepository){
            this.JPAUserRepository = JPAUserRepository;
    }
    @Override
    public ValidationResult validate(CreateUserDto toValidate) {
            return JPAUserRepository.findByEmail(toValidate.getEmail()).isPresent() ?
                    ValidationResult.invalid(String.format("User with email [%s] already exists",toValidate.getEmail())) :
                    checkNext(toValidate);
    }
}
