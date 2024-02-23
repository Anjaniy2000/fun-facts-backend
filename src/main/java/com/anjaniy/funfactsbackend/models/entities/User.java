package com.anjaniy.funfactsbackend.models.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "users")
public class User extends AbstractEntity<UUID> {
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fact> facts;
    @OneToMany(mappedBy = "likedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;
    @OneToMany(mappedBy = "dislikedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dislike> dislikes;
    @OneToMany(mappedBy = "superLikedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SuperLike> superLikes;

    public User() {

    }

    public User(
        UUID id,
        String userName,
        String email,
        String password,
        List<Fact> facts,
        List<Like> likes,
        List<Dislike> dislikes,
        List<SuperLike> superLikes) {

        setId(id);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.facts = facts;
        this.likes = likes;
        this.dislikes = dislikes;
        this.superLikes = superLikes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Dislike> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Dislike> dislikes) {
        this.dislikes = dislikes;
    }

    public List<SuperLike> getSuperLikes() {
        return superLikes;
    }

    public void setSuperLikes(List<SuperLike> superLikes) {
        this.superLikes = superLikes;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + getId() + '\'' +
            "userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", facts=" + facts +
            ", likes=" + likes +
            ", dislikes=" + dislikes +
            ", superLikes=" + superLikes +
            '}';
    }
}
