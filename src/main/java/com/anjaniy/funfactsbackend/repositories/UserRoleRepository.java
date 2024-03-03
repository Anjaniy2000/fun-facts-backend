package com.anjaniy.funfactsbackend.repositories;

import com.anjaniy.funfactsbackend.models.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    Optional<UserRole> findByName(String name);
}
