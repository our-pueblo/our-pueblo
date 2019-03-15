package com.codeup.ourpueblo.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, length = 1000)
    private String web_page;

    @Column(columnDefinition = "text")
    private String untranslated_text;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    private Department department;

    @OneToOne
    private User user;

    @Column(columnDefinition = "text")
    private String google_translate;

    @OneToOne
    private Request_Status status;


    //TODO Ask about this
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date time;


    public Request(){}

    public Request (String web_page, String untranslated_text, String description, Department department_id, User user_id, String google_translate, Request_Status status_id, Date time){
        this.web_page = web_page;
        this.untranslated_text = untranslated_text;
        this.description = description;
        this.department = department_id;
        this.user = user_id;
        this.google_translate = google_translate;
        this.status = status_id;
        this.time = time;
    }

    public Request(String web_page, String description, Department department_id, Request_Status status) {
        this.web_page = web_page;
        this.description = description;
        this.department = department_id;
        this.status = status;
    }

    public Request(String web_page, String description, Department department_id, User user_id, Request_Status status) {
        this.web_page = web_page;
        this.description = description;
        this.department = department_id;
        this.user = user_id;
        this.status = status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeb_page() {
        return web_page;
    }

    public void setWeb_page(String web_page) {
        this.web_page = web_page;
    }

    public String getUntranslated_text() {
        return untranslated_text;
    }

    public void setUntranslated_text(String untranslated_text) {
        this.untranslated_text = untranslated_text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment_id() {
        return department;
    }

    public void setDepartment_id(Department department_id) {
        this.department = department_id;
    }

    public User getUser_id() {
        return user;
    }

    public void setUser_id(User user_id) {
        this.user = user_id;
    }

    public String getGoogle_translate() {
        return google_translate;
    }

    public void setGoogle_translate(String google_translate) {
        this.google_translate = google_translate;
    }

    public Request_Status getStatus() {
        return status;
    }

    public void setStatus(Request_Status status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
