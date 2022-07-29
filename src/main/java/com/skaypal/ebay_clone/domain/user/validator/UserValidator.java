package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.steps.create.AfmValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create.EmailValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create.PhoneValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create.UsernameValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update.UpdateEmailValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update.UpdatePhoneValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update.UpdateUsernameValidation;
import com.skaypal.ebay_clone.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {

    UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ValidationResult validateCreateUserDto(CreateUserDto user) {
        return new UsernameValidation(userRepository)
                .linkWith(new EmailValidation(userRepository))
                .linkWith(new AfmValidation(userRepository))
                .linkWith(new PhoneValidation(userRepository))
                .validate(user);
    }

    public ValidationResult validateUpdateUserDto(UpdateUserDto user) {
        return new UpdateEmailValidation(userRepository)
                .linkWith(new UpdatePhoneValidation(userRepository))
                .linkWith(new UpdateUsernameValidation(userRepository))
                .validate(user);
    }
}
