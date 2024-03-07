package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "reset_password_tokens")
@Entity
public class ResetPasswordToken extends AbstractEntity<UUID> {
    @Column(name = "token", nullable = false)
    private UUID token;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    public ResetPasswordToken() {

    }

    public ResetPasswordToken(UUID token, User user, LocalDateTime expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ResetPasswordToken{" +
            "token=" + token +
            ", user=" + user +
            ", expiryDate=" + expiryDate +
            '}';
    }
}
