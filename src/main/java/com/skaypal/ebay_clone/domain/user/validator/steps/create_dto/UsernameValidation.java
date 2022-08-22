package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class UsernameValidation extends ValidationStep<CreateUserDto> {

    private UserRepository userRepository;


    public UsernameValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ValidationResult validate(CreateUserDto toValidate) {

        return userRepository.findByUsername(toValidate.getUsername()).isPresent() ?
                ValidationResult.invalid(String.format("User with username [%s] already exists", toValidate.getUsername())) :
                checkNext(toValidate);
    }
}
