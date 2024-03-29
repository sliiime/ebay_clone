package com.skaypal.ebay_clone.domain.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class UpdateUserDto {

    @Null
    Integer id;
    @Length(max = 15)
    private String username;

    @Length(min = 8,max =30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[\\[-`!-//:-@-'{-~])(?=.*\\d).*$")
    private String password;

    @Length(max = 50)
    private String address;

    @Length(min = 8 ,max = 12)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    @Email
    private String email;

    public Integer getId() {return id;}

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id){this.id = id;}

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
