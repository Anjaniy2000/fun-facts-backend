package com.anjaniy.funfactsbackend.models.entities;

import com.anjaniy.funfactsbackend.models.entities.abstractEntity.AbstractEntity;
import jakarta.persistence.*;

@Table
@Entity(name = "super_likes")
public class SuperLike extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fact_id_fk", referencedColumnName = "id")
    private Fact fact;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User superLikedBy;

    public SuperLike() {
    }

    public SuperLike(
        Fact fact,
        User superLikedBy) {

        this.fact = fact;
        this.superLikedBy = superLikedBy;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public User getSuperLikedBy() {
        return superLikedBy;
    }

    public void setSuperLikedBy(User superLikedBy) {
        this.superLikedBy = superLikedBy;
    }

    @Override
    public String toString() {
        return "SuperLike{" +
            "id=" + getId() +
            "fact=" + fact +
            ", superLikedBy=" + superLikedBy +
            '}';
    }
}
