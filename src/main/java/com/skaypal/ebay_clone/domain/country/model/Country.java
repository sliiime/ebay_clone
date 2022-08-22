package com.skaypal.ebay_clone.domain.country.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    public String country;

    @Column
    public String iso;

    public Country() {
    }

    public Country(Integer id,
                   String country,
                   String iso
    ) {
        this.id = id;
        this.country = country;
        this.iso = iso;
    }

    public Country(String country,
                   String iso
    ) {
        this.country = country;
        this.iso = iso;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getIso() {
        return iso;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
