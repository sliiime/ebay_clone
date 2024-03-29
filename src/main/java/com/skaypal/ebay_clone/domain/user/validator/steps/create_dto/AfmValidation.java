package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class AfmValidation extends ValidationStep<CreateUserDto> {

    UserRepository userRepository;

    public AfmValidation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ValidationResult validate(CreateUserDto toValidate) {
            return userRepository.findByAfm(toValidate.getAfm()).isPresent() ?
                    ValidationResult.invalid(String.format("User with AFM [%s] already exists",toValidate.getAfm())) :
                    checkNext(toValidate);
    }
}
