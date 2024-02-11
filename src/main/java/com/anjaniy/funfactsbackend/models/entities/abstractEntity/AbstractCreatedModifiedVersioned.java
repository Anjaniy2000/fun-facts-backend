package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class AbstractCreatedModifiedVersioned extends AbstractCreatedModified implements Versioned {

    @Version
    @Column(name = "version", nullable = false)
    protected Short version;

    public AbstractCreatedModifiedVersioned() {
    }

    public Short getVersion() {
        return this.version;
    }

    public void setVersion(final Short version) {
        this.version = version;
    }
}
