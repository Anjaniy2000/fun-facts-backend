package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;
import java.time.LocalDateTime;

public class DateFieldListener {

    public DateFieldListener() {
    }

    @PrePersist
    public void setPrePersistAuditProperties(final Object entity) {
        Instant now = Instant.now();
        if (entity instanceof Created) {
            ((Created)entity).setCreatedAt(LocalDateTime.now());
        }

        if (entity instanceof Modified) {
            ((Modified)entity).setModifiedAt(LocalDateTime.now());
        }

    }

    @PreUpdate
    public void setPreUpdateAuditProperties(final Object entity) {
        if (entity instanceof Modified) {
            ((Modified)entity).setModifiedAt(LocalDateTime.now());
        }

    }

}
