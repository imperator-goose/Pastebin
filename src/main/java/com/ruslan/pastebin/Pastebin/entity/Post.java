package com.ruslan.pastebin.Pastebin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts",
        uniqueConstraints = {
        @UniqueConstraint(name = "uq_posts_short_code", columnNames = "short_code")
        })
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT CONSTRAINT pk_posts_id PRIMARY KEY", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @Lob
    private String content;

    @Column(name = "short_code",length = 20,nullable = false)
    private String shortCode;

    @Column(name = "status",nullable = false, columnDefinition = "TINYINT DEFAULT 1 CONSTRAINT ck_posts_status CHECK (status IN (0, 1))")
    private Integer status = 1;

    @Column(name = "views", columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer views = 0;

    @Column(name = "upvotes",columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer upvotes = 0;

    @Column(name = "downvotes",columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer downvotes = 0;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_posts_author_id"),nullable = false) // Столбец в таблице posts
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    // Constructors, getters, and setters
    public Post() {}

    public Post(String title, String content, User author, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
