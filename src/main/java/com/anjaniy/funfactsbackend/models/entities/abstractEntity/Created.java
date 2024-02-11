package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import java.time.LocalDateTime;

public interface Created {
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime instant);
}
