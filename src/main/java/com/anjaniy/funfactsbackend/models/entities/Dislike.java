package com.anjaniy.funfactsbackend.models.entities;

import com.anjaniy.funfactsbackend.models.entities.abstractEntity.AbstractEntity;
import jakarta.persistence.*;

@Table
@Entity(name = "dislikes")
public class Dislike extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fact_id_fk", referencedColumnName = "id")
    private Fact fact;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User dislikedBy;

    public Dislike() {

    }

    public Dislike(
        Fact fact,
        User dislikedBy) {

        this.fact = fact;
        this.dislikedBy = dislikedBy;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public User getDislikedBy() {
        return dislikedBy;
    }

    public void setDislikedBy(User dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    @Override
    public String toString() {
        return "Dislike{" +
            "id=" + getId() +
            "fact=" + fact +
            ", dislikedBy=" + dislikedBy +
            '}';
    }
}
