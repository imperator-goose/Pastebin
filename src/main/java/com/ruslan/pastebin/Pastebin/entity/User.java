package com.ruslan.pastebin.Pastebin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_username", columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT CONSTRAINT pk_users_id PRIMARY KEY")
    private Long id;

    @Column(name = "username",nullable = false, length = 50)
    private String username;


    @Column(name = "password",nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "status",nullable = false, columnDefinition = "TINYINT DEFAULT 1 CONSTRAINT ck_users_status CHECK (status IN (0, 1))")
    private Integer status = 1;

    @OneToMany(mappedBy = "author") // "author" - это поле в классе Post
    @JsonIgnore
    private List<Post> posts;



    // Constructors, getters, and setters
    public User() {}

    public User(String username,  String password, List<Post> posts) {
        this.username = username;
        this.password = password;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
