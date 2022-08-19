package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.domain.user.validator.steps.create_dto.AfmValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create_dto.EmailValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create_dto.PhoneValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.create_dto.UsernameValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdateEmailValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdatePhoneValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdateUsernameValidation;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {

    JPAUserRepository JPAUserRepository;

    @Autowired
    public UserValidator(JPAUserRepository JPAUserRepository) {
        this.JPAUserRepository = JPAUserRepository;
    }


    public ValidationResult validateCreateUserDto(CreateUserDto user) {
        return new UsernameValidation(JPAUserRepository)
                .linkWith(new EmailValidation(JPAUserRepository))
                .linkWith(new AfmValidation(JPAUserRepository))
                .linkWith(new PhoneValidation(JPAUserRepository))
                .validate(user);
    }

    public ValidationResult validateUpdateUserDto(UpdateUserDto user) {
        return new UpdateEmailValidation(JPAUserRepository)
                .linkWith(new UpdatePhoneValidation(JPAUserRepository))
                .linkWith(new UpdateUsernameValidation(JPAUserRepository))
                .validate(user);
    }

    public boolean userExists(Integer id){
        return JPAUserRepository.findById(id).isPresent();
    }


}
