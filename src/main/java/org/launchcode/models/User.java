package org.launchcode.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
    // set fields

    @Id
    @GeneratedValue
    private int userId;

    @NotNull
    @Size(min=5, max=15, message = "Username must be between 5 and 15 characters")
    private String username;

    @Email(message = "Error with email")
    private String email;

    @NotNull
    @Size(min=3, message = "Password must be at least 3 characters long")
    private String password;

    @NotNull
    @Size(min=3, message = "Password and verification did not match")
    private String verify;

    //constructors
    public User(String username, String email, String password, String verify) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.verify = verify;
    }

    public User () { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}
