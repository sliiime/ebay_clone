package com.skaypal.ebay_clone.domain.user.dto;

import com.skaypal.ebay_clone.domain.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ViewUserDto {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String afm;
    private String phone;
    private Float rating;

    private String country;
    private List<Integer> itemIds;


    public ViewUserDto() {
    }
    public ViewUserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.address = user.getAddress();
        this.afm = user.getAfm();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.rating = user.getRating();
        this.password = user.getPassword();
        this.itemIds = user.getItems().stream().map((i) -> i.getId()).collect(Collectors.toList());
        this.country = user.getCountry().getCountry();
    }

    public Integer getId() { return id;}

    public List<Integer> getItemIds() { return itemIds;}

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

    public Float getRating() { return rating;}

    public String getCountry() {return country;}

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Float rating) {this.rating = rating;}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) { this.id = id;}

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

    public void setItemIds(List<Integer> itemIds) { this.itemIds = itemIds;}

    private void setCountry(String country) {this.country = country;}
}
