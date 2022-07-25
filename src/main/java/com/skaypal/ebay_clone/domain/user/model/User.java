package com.skaypal.ebay_clone.domain.user.model;

import com.skaypal.ebay_clone.UserRegStatus;

import javax.persistence.*;

@Entity
@Table()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String afm;
    private Float rating;
    @Column(name = "registered")
    @Enumerated(EnumType.STRING)
    private UserRegStatus registrationStatus;
    @Column(name = "phone")
    private String phoneNumber;

    public User(){}

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
                String phoneNumber){

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
        this.phoneNumber = phoneNumber;
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
                String phoneNumber){

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.afm = afm;
        this.rating = rating;
        this.registrationStatus = registrationStatus;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public Float getRating() {
        return rating;
    }

    public String getUsername(){
        return username;
    }

    public String getSurname(){
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
