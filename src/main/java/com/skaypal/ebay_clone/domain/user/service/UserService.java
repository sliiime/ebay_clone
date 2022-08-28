package com.skaypal.ebay_clone.domain.user.service;

import com.skaypal.ebay_clone.domain.country.exception.CountryNotFoundException;
import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.country.service.CountryService;
import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.role.repository.RoleRepository;
import com.skaypal.ebay_clone.domain.role.service.RoleService;
import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.UpdateUserDto;
import com.skaypal.ebay_clone.domain.user.dto.ViewUserDto;
import com.skaypal.ebay_clone.domain.user.exceptions.UserConflictException;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.skaypal.ebay_clone.domain.user.UserRegStatus.ACCEPTED;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;
    private CountryService countryService;
    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    Role UNAUTHORIZED;
    Role AUTHORIZED;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserValidator userValidator,
                       CountryService countryService,
                       RoleService roleService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.countryService = countryService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

        UNAUTHORIZED = roleService.getRole("UNAUTHORIZED_USER").orElseThrow(() -> new RuntimeException("FATAL : UNAUTHORIZED_USER ROLE DOES NOT EXIST"));
        AUTHORIZED = roleService.getRole("AUTHORIZED_USER").orElseThrow(() -> new RuntimeException("FATAL : AUTHORIZED_USER ROLE DOES NOT EXIST"));

    }

    public List<ViewUserDto> getUsers() {
        return userRepository.findAll().stream().map((u) -> new ViewUserDto(u)).collect(Collectors.toList());
    }

    public ViewUserDto getUser(Integer id) throws UserNotFoundException {

        return new ViewUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id.toString())));
    }


    public ViewUserDto createUser(CreateUserDto createUserDto) throws UserConflictException {


        ValidationResult validationResult = userValidator.validateCreateUserDto(createUserDto);

        if (!validationResult.isValid()) throw new UserConflictException(validationResult.getErrorMessage());


        Country country = countryService.getCountry(createUserDto.getCountry());

        List<Role> roles = List.of(UNAUTHORIZED);

        if (country == null) throw new CountryNotFoundException(createUserDto.getCountry());

        User user = new User(createUserDto);
        user.setCountry(country);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRoles(roles);

        return new ViewUserDto(userRepository.save(user));

    }


    public void updateUser(UpdateUserDto updateUserDto) throws UserConflictException, UserNotFoundException {


        ValidationResult validationResult = userValidator.validateUpdateUserDto(updateUserDto);

        if (!validationResult.isValid()) throw new UserConflictException(validationResult.getErrorMessage());

        User user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new UserNotFoundException("id", updateUserDto.getId().toString()));


        //Checking which fields need to be updated
        if (updateUserDto.getUsername() != null)
            user.setUsername(updateUserDto.getUsername());
        if (updateUserDto.getPassword() != null)
            user.setPassword(updateUserDto.getPassword());
        if (updateUserDto.getEmail() != null)
            user.setEmail(updateUserDto.getEmail());
        if (updateUserDto.getPhone() != null)
            user.setPhone(updateUserDto.getPhone());

        userRepository.save(user);


    }

    public void deleteUser(Integer id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id.toString()));
        userRepository.delete(user);
    }


    @Transactional
    public boolean approveUser(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty() || UserRegStatus.DECLINED.equals(user.get().getRegistrationStatus())) return false;

        User u = user.get();

        List<Role> roles = new ArrayList<>();
        roles.add(AUTHORIZED);

        u.setRoles(roles);
        u.setRegistrationStatus(ACCEPTED);

        userRepository.save(u);

        return true;

    }
}
