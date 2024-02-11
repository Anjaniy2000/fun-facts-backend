package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import java.io.Serializable;

public interface Versioned extends Serializable {
    Short getVersion();
    void setVersion(Short version);
}
