package com.skaypal.ebay_clone.domain.user.validator.steps.update_dto;

import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.validator.ValidationResult;
import com.skaypal.ebay_clone.validator.ValidationStep;

import java.util.Optional;

public class UpdateEmailValidation extends ValidationStep<UpdateUserDto> {
    UserRepository userRepository;

    public UpdateEmailValidation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ValidationResult validate(UpdateUserDto toValidate) {
        if (toValidate.getEmail() != null) {
            Optional<User> u = userRepository.findByUsername(toValidate.getEmail());
            if (u.isPresent()){
                User user = u.get();
                if ( user.getId() != toValidate.getId()) return ValidationResult.invalid(String.format("User with email [%s] already exists",toValidate.getUsername()));
            }
        }

        return checkNext(toValidate);
    }
}
