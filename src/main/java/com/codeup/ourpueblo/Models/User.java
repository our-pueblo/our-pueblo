package com.codeup.ourpueblo.Models;

public class User {
    private String username;
    private String password;
    private String email;
    private boolean admin;
    private boolean active;
    private boolean recieve_emails;
    private String phone_number;
    private String security_quaestion;

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
        return recieve_emails;
    }

    public void setRecieve_emails(boolean recieve_emails) {
        this.recieve_emails = recieve_emails;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSecurity_quaestion() {
        return security_quaestion;
    }

    public void setSecurity_quaestion(String security_quaestion) {
        this.security_quaestion = security_quaestion;
    }
}
