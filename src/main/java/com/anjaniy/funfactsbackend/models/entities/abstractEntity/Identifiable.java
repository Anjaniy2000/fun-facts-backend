package com.anjaniy.funfactsbackend.models.entities.abstractEntity;

import java.io.Serializable;

public interface Identifiable<T extends Serializable>{
    T getId();
}
