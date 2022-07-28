package com.skaypal.ebay_clone.domain.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UpdateUserDto {

    @Length(max = 15)
    private String username;

    @Length(min = 8,max =30)
    private String password;

    @Length(max = 50)
    private String address;

    @Length(min = 8 ,max = 12)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    @Email
    private String email;

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
