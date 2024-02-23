package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Table
@Entity(name = "likes")
public class Like extends AbstractEntity<UUID> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fact_id_fk", referencedColumnName = "id")
    private Fact fact;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User likedBy;

    public Like() {

    }

    public Like(
        UUID id,
        Fact fact,
        User likedBy) {

        setId(id);
        this.fact = fact;
        this.likedBy = likedBy;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public User getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(User likedBy) {
        this.likedBy = likedBy;
    }

    @Override
    public String toString() {
        return "Like{" +
            "id=" + getId() +
            "fact=" + fact +
            ", likedBy=" + likedBy +
            '}';
    }
}
