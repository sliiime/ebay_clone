package com.skaypal.ebay_clone.domain.user.validator.steps.update_dto;

import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

import java.util.Optional;

public class UpdateUsernameValidation extends ValidationStep<UpdateUserDto> {

    JPAUserRepository JPAUserRepository;

    public UpdateUsernameValidation(JPAUserRepository JPAUserRepository){
        this.JPAUserRepository = JPAUserRepository;
    }

    @Override
    public ValidationResult validate(UpdateUserDto toValidate) {
        if (toValidate.getUsername() != null) {
            Optional<User> u = JPAUserRepository.findByUsername(toValidate.getUsername());
            if (u.isPresent()){
                User user = u.get();
                if ( user.getId() != toValidate.getId()) return ValidationResult.invalid(String.format("User with username [%s] already exists",toValidate.getUsername()));

            }
        }

        return checkNext(toValidate);
    }
}
