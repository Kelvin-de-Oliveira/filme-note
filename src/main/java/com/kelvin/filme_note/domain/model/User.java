package com.kelvin.filme_note.domain.model;

import jakarta.persistence.GeneratedValue;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String passwordHash;

    private Role role;
    private List<Review> reviews;
    private List<Like> likes;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public List<Like> getLikes() { return likes; }
    public void setLikes(List<Like> likes) { this.likes = likes; }

}
