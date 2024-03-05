package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "verification_tokens")
@Entity
public class VerificationToken extends AbstractEntity<UUID> {

    @Column(name = "token", nullable = false)
    private UUID token;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(UUID token, User user) {
        this.token = token;
        this.user = user;
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

    @Override
    public String toString() {
        return "VerificationToken{" +
            "token='" + token + '\'' +
            ", user=" + user +
            '}';
    }
}
