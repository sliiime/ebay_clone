package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.validator.ValidationResult;
import com.skaypal.ebay_clone.validator.ValidationStep;

public class PhoneValidation extends ValidationStep<CreateUserDto> {

    UserRepository userRepository;

    public PhoneValidation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ValidationResult validate(CreateUserDto toValidate) {
        return userRepository.findByPhone(toValidate.getPhone()).isPresent() ?
        ValidationResult.invalid(String.format("User with email [%s] already exists",toValidate.getPhone())):
        checkNext(toValidate);
    }
}
