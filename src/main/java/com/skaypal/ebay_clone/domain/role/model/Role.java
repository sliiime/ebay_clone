package com.skaypal.ebay_clone.domain.role.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table()
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
