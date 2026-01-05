package com.CodingDupo.projectAPI.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique=true,nullable = false)
    private String username;
    private String password;
    private String role;
    private LocalDateTime firstSeen;
    private LocalDateTime lastSeen;
    protected Users() {
        // REQUIRED by JPA
    }
    public LocalDateTime getFirstSeen() {
        return firstSeen;
    }
    public void setFirstSeen(LocalDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }
    public LocalDateTime getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getId() {
        return id;
    }
    @Column(unique = true)
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
