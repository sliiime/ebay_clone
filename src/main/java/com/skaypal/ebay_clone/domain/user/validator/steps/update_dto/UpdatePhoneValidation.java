package com.skaypal.ebay_clone.domain.user.validator.steps.update_dto;

import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

import java.util.Optional;

public class UpdatePhoneValidation extends ValidationStep<UpdateUserDto> {
    JPAUserRepository JPAUserRepository;

    public UpdatePhoneValidation(JPAUserRepository JPAUserRepository){
        this.JPAUserRepository = JPAUserRepository;
    }

    @Override
    public ValidationResult validate(UpdateUserDto toValidate) {
        if (toValidate.getPhone() != null) {
            Optional<User> u = JPAUserRepository.findByPhone(toValidate.getPhone());
            if (u.isPresent()){
                User user = u.get();
                if ( user.getId() != toValidate.getId()) return ValidationResult.invalid(String.format("User with email [%s] already exists",toValidate.getUsername()));
            }
        }

        return checkNext(toValidate);
    }
}
