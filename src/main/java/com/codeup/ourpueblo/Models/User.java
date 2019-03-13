package com.codeup.ourpueblo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @JsonBackReference
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 45)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column
    private boolean admin;

    @Column
    private boolean active;

    @Column
    private boolean receive_emails;

    @Column(length = 12)
    private String phone_number;

    @Column(length = 100)
    private String security_question;


    public User(){

    }

    public User(String username, String password, String email, boolean admin, boolean active, boolean receive_emails, String phone_number, String security_question) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.active = active;
        this.receive_emails = receive_emails;
        this.phone_number = phone_number;
        this.security_question = security_question;
    }

    public User(long id, String username, String password, String email, boolean admin, boolean active, boolean receive_emails, String phone_number, String security_question) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.active = active;
        this.receive_emails = receive_emails;
        this.phone_number = phone_number;
        this.security_question = security_question;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRecieve_emails() {
        return receive_emails;
    }

    public void setRecieve_emails(boolean receive_emails) {
        this.receive_emails = receive_emails;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
