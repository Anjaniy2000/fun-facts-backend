package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractCreatedModified extends AbstractCreated implements Modified {

    @Column(name = "modified", nullable = false)
    private LocalDateTime lastModifiedDate;

    public AbstractCreatedModified() {
    }

    public LocalDateTime getModifiedAt() {
        return this.lastModifiedDate;
    }

    public void setModifiedAt(final LocalDateTime instant) {
        this.lastModifiedDate = instant;
    }
}
