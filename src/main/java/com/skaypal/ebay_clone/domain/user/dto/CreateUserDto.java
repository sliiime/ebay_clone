package com.skaypal.ebay_clone.domain.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class  CreateUserDto {
    @NotNull
    @Length(max = 15)
    private String username;
    @NotNull
    @Length(min = 8,max =30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[\\[-`!-//:-@-'{-~])(?=.*\\d).*$")
    private String password;
    @NotNull
    @Length(max = 50)
    private String name;
    @NotNull
    @Length(max = 50)
    private String surname;
    @NotNull

    private String address;
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "[0-9]{9}")
    private String afm;

    @NotBlank
    @Length(min = 8 ,max = 12)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    public CreateUserDto() {
    }

    public CreateUserDto(Integer id,
                         String username,
                         String password,
                         String name,
                         String surname,
                         String address,
                         String email,
                         String afm,
                         String phoneNumber) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.afm = afm;
        this.phone = phoneNumber;
    }

    public CreateUserDto(String username,
                        String password,
                        String name,
                        String surname,
                        String address,
                        String email,
                        String afm,
                        String phoneNumber) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.afm = afm;
        this.phone = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getAfm() {
        return afm;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
