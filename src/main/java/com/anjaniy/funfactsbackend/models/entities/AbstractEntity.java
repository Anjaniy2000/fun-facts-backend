package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class AbstractEntity<T> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
