package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({DateFieldListener.class})
public class AbstractCreated implements Created {

    @Column(name = "created",  nullable = false)
    private LocalDateTime createdDate;

    public AbstractCreated() {
    }

    public LocalDateTime getCreatedAt() {
        return this.createdDate;
    }


    public void setCreatedAt(final LocalDateTime instant) {
        this.createdDate = instant;
    }
}
