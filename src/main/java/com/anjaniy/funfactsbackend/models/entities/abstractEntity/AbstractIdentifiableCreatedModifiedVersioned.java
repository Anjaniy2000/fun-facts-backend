package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractIdentifiableCreatedModifiedVersioned<T extends Serializable> extends AbstractCreatedModifiedVersioned implements Identifiable<T> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;

    public AbstractIdentifiableCreatedModifiedVersioned() {
    }

    @Override
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
