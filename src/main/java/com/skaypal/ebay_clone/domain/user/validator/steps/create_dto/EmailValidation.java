package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class EmailValidation extends ValidationStep<CreateUserDto> {

    UserRepository userRepository;

    public EmailValidation(UserRepository userRepository){
            this.userRepository = userRepository;
    }
    @Override
    public ValidationResult validate(CreateUserDto toValidate) {
            return userRepository.findByEmail(toValidate.getEmail()).isPresent() ?
                    ValidationResult.invalid(String.format("User with email [%s] already exists",toValidate.getEmail())) :
                    checkNext(toValidate);
    }
}
