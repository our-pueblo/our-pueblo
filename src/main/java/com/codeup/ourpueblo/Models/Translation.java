package com.codeup.ourpueblo.Models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "translations")
public class Translation {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User user;

    @Column(length = 9999)
    private String userTranslation;

    @Column(columnDefinition = "text")
    private String description_of_changes;

    @Column (nullable = false)
    private boolean flag_problem;

    @Column(columnDefinition = "text")
    private String reason;

    @OneToOne
    private Request request;

    @OneToOne
    private Translation_Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getUserTranslation() {
        return userTranslation;
    }

    public void setUserTranslation(String userTranslation) {
        this.userTranslation = userTranslation;
    }

    public String getDescription_of_changes() {
        return description_of_changes;
    }

    public void setDescription_of_changes(String description_of_changes) {
        this.description_of_changes = description_of_changes;
    }

    public boolean isFlag_problem() {
        return flag_problem;
    }

    public void setFlag_problem(boolean flag_problem) {
        this.flag_problem = flag_problem;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Translation_Status getStatus() {
        return status;
    }

    public void setStatus(Translation_Status status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date time;

    public Translation(){}

    public Translation(User user, String userTranslation, boolean flag_problm, String reason, Request request, Translation_Status status, Date time) {
        this.user = user;
        this.userTranslation = userTranslation;
        this.flag_problem = flag_problm;
        this.reason = reason;
        this.request = request;
        this.status = status;
        this.time = time;
    }


}
