package com.skaypal.ebay_clone.domain.user.model;

import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NotNull
    @Length(max = 15)
    private String username;
    @NotNull
    @Length(min = 8,max =30)
    private String password;

    @NotNull
    @Length(max = 50)
    private String name;

    @NotNull
    @Length(max = 50)
    private String surname;

    @NotNull
    @Length(max = 50)
    private String address;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "[0-9]{9}")
    private String afm;
    private Float rating;
    @Column(name = "registered")
    @Enumerated(EnumType.STRING)
    private UserRegStatus registrationStatus;
    @Column(name = "phone",unique = true)
    @NotNull
    @Length(min = 8 ,max = 12)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    public User() {
    }

    public User(Integer id,
                String username,
                String password,
                String name,
                String surname,
                String address,
                String email,
                String afm,
                Float rating,
                UserRegStatus registrationStatus,
                String phone) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.afm = afm;
        this.rating = rating;
        this.registrationStatus = registrationStatus;
        this.phone = phone;
    }

    public User(String username,
                String password,
                String name,
                String surname,
                String address,
                String email,
                String afm,
                Float rating,
                UserRegStatus registrationStatus,
                String phone) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.afm = afm;
        this.rating = rating;
        this.registrationStatus = registrationStatus;
        this.phone = phone;
    }

    public User(CreateUserDto user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.afm = user.getAfm();
        this.rating = 0F;
        this.registrationStatus = UserRegStatus.PENDING;
        this.phone = user.getPhoneNumber();
    }

    public Integer getId() {
        return id;
    }

    public Float getRating() {
        return rating;
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

    public UserRegStatus getRegistrationStatus() {
        return registrationStatus;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setRegistrationStatus(UserRegStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
