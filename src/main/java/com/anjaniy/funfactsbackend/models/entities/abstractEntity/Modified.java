package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import java.time.LocalDateTime;

public interface Modified {
    LocalDateTime getModifiedAt();
    void setModifiedAt(LocalDateTime instant);
}
