package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class AbstractEntity extends AbstractIdentifiableCreatedModifiedVersioned<UUID>{
    public AbstractEntity(){

    }

}
