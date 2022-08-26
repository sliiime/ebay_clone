package com.skaypal.ebay_clone.domain.user.model;

import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import org.hibernate.boot.model.source.spi.FetchCharacteristics;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String address;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String afm;
    private Float rating;
    @Column(name = "registered")
    @Enumerated(EnumType.STRING)
    private UserRegStatus registrationStatus;
    @Column(name = "phone", unique = true)
    private String phone;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Item> items;

    @ManyToOne
    private Country country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )

    private List<Role> roles = List.of(new Role(1,"USER"));

    public User() {}

    public User(Integer id) {
        this.id = id;
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
                String phone,
                Country country) {

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
        this.country = country;
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
                String phone,
                Country country) {

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
        this.country = country;
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
        this.phone = user.getPhone();
        this.items = new ArrayList<>();
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

    public Country getCountry() {
        return country;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserRegStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public List<Item> getItems() {
        return this.items;
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

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }

}
