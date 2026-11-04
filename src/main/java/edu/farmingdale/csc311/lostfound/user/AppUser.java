package edu.farmingdale.csc311.lostfound.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    /** BCrypt hash, never the plain-text password. */
    @Column(nullable = false)
    private String password;

    protected AppUser() {
        // required by JPA
    }

    /**
     * @param passwordHash an already-encoded BCrypt hash (use PasswordEncoder.encode)
     */
    public AppUser(String username, String passwordHash) {
        this.username = username;
        this.password = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }
}
