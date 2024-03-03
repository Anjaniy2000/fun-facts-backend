package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity <T extends Serializable> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
