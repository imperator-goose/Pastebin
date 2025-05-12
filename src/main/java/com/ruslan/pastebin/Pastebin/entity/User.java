package com.ruslan.pastebin.Pastebin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_username", columnNames = "username")
        })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT CONSTRAINT pk_users_id PRIMARY KEY", nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role; // ROLE_USER или ROLE_ADMIN

    @Column(name = "username",nullable = false, length = 50)
    private String username;


    @Column(name = "password",nullable = false)
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() { return true; }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() { return status == 1; }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return status == 1; }
}
