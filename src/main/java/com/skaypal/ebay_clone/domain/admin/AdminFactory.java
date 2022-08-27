package com.skaypal.ebay_clone.domain.admin;

import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.role.model.RoleEnum;
import com.skaypal.ebay_clone.domain.user.model.User;

import java.util.List;
import static com.skaypal.ebay_clone.domain.user.UserRegStatus.*;


public class AdminFactory {


    private AdminFactory(){}
    public static User admin (String username,
                        String password,
                        String name,
                        String surname,
                        String address,
                        String email,
                        String afm,
                        String phone,
                        Country country) {

        return new User(
                username,
                password,
                name,
                surname,
                address,
                email,
                afm,
                5f,
                ACCEPTED,
                phone,
                country,
                RoleEnum.ADMIN
        );



    }
}
