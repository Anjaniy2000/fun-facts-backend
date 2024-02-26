package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Table
@Entity(name = "user_roles")
public class UserRole extends AbstractEntity<UUID> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public UserRole() {

    }

    public UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
            "id='" + getId() + '\'' +
            "name='" + name + '\'' +
            '}';
    }

}
