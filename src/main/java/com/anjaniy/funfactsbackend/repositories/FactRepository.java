package com.anjaniy.funfactsbackend.repositories;

import com.anjaniy.funfactsbackend.models.entities.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactRepository extends JpaRepository<Fact, UUID> {
}
