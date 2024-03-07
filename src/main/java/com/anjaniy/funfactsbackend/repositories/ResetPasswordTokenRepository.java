package com.anjaniy.funfactsbackend.repositories;

import com.anjaniy.funfactsbackend.models.entities.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, UUID> {

    Optional<ResetPasswordToken> findByToken(UUID token);
}
