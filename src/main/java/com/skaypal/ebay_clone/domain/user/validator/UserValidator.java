package com.skaypal.ebay_clone.domain.user.validator;

import com.skaypal.ebay_clone.domain.country.validator.CountryValidator;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.steps.create_dto.*;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdateEmailValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdatePhoneValidation;
import com.skaypal.ebay_clone.domain.user.validator.steps.update_dto.UpdateUsernameValidation;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserValidator {

    private UserRepository userRepository;
    private CountryValidator countryValidator;
    @Autowired
    public UserValidator(UserRepository userRepository, CountryValidator countryValidator) {
        this.userRepository = userRepository;
        this.countryValidator = countryValidator;
    }


    public ValidationResult validateCreateUserDto(CreateUserDto user) {
        return new UsernameValidation(userRepository)
                .linkWith(new EmailValidation(userRepository))
                .linkWith(new AfmValidation(userRepository))
                .linkWith(new PhoneValidation(userRepository))
                .linkWith(new CountryValidation(countryValidator))
                .validate(user);
    }

    public ValidationResult validateUpdateUserDto(UpdateUserDto user) {
        return new UpdateEmailValidation(userRepository)
                .linkWith(new UpdatePhoneValidation(userRepository))
                .linkWith(new UpdateUsernameValidation(userRepository))
                .validate(user);
    }

    public boolean userExists(Integer id){
        return userRepository.findById(id).isPresent();
    }


}
