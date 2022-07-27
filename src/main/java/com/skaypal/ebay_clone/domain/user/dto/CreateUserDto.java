package com.skaypal.ebay_clone.domain.user.dto;

import org.springframework.validation.annotation.Validated;

public class CreateUserDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String afm;

    private String phoneNumber;

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
        this.phoneNumber = phoneNumber;
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
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
