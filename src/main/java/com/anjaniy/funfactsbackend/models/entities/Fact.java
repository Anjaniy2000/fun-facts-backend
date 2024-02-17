package com.anjaniy.funfactsbackend.models.entities;

import com.anjaniy.funfactsbackend.models.enums.Category;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "facts")
public class Fact extends AbstractEntity<UUID> {
    @Column(name = "fact", nullable = false)
    private String fact;
    @Column(name = "source", nullable = false)
    private String source;
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User postedBy;
    @OneToMany(mappedBy = "fact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;
    @OneToMany(mappedBy = "fact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dislike> dislikes;
    @OneToMany(mappedBy = "fact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SuperLike> superLikes;
    @Column(name = "likes_count")
    private Integer likesCount;
    @Column(name = "dislikes_count")
    private Integer dislikesCount;
    @Column(name = "super_likes_count")
    private Integer superLikesCount;
    @Column(name = "is_disputed")
    private Boolean isDisputed;

    public Fact() {

    }

    public Fact(
        UUID id,
        String fact,
        String source,
        Category category,
        User postedBy,
        List<Like> likes,
        List<Dislike> dislikes,
        List<SuperLike> superLikes,
        Integer likesCount,
        Integer dislikesCount,
        Integer superLikesCount,
        Boolean isDisputed) {

        this.fact = fact;
        this.source = source;
        this.category = category;
        this.postedBy = postedBy;
        this.likes = likes;
        this.dislikes = dislikes;
        this.superLikes = superLikes;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.superLikesCount = superLikesCount;
        this.isDisputed = isDisputed;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
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

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(Integer dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public Integer getSuperLikesCount() {
        return superLikesCount;
    }

    public void setSuperLikesCount(Integer superLikesCount) {
        this.superLikesCount = superLikesCount;
    }

    public Boolean getDisputed() {
        return isDisputed;
    }

    public void setDisputed(Boolean disputed) {
        isDisputed = disputed;
    }

    @Override
    public String toString() {
        return "Fact{" +
            "id='" + getId() + '\'' +
            "fact='" + fact + '\'' +
            ", source='" + source + '\'' +
            ", category=" + category +
            ", postedBy=" + postedBy +
            ", likes=" + likes +
            ", dislikes=" + dislikes +
            ", superLikes=" + superLikes +
            ", likesCount=" + likesCount +
            ", dislikesCount=" + dislikesCount +
            ", superLikesCount=" + superLikesCount +
            ", isDisputed=" + isDisputed +
            '}';
    }
}
