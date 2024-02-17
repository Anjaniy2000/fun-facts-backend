package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class AbstractEntity <T extends Serializable> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    public AbstractEntity() {

    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
