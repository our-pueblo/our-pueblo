package com.codeup.ourpueblo.Models;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 1000)
    private String web_page;

    @Column
    private String untranslated_text;

    @Column
    private String description;

    @Column
    private long department_id;

    @Column
    private long user_id;

    @Column(nullable = true)
    private String google_translate;

    @Column
    private int status_id;

    @CreationTimestamp
    private Timestamp time;

    public Request(String web_page, String untranslated_text, String description, long department_id, long user_id, String google_translate, int status_id, Timestamp time) {
        this.web_page = web_page;
        this.untranslated_text = untranslated_text;
        this.description = description;
        this.department_id = department_id;
        this.user_id = user_id;
        this.google_translate = google_translate;
        this.status_id = status_id;
        this.time = time;
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

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getGoogle_translate() {
        return google_translate;
    }

    public void setGoogle_translate(String google_translate) {
        this.google_translate = google_translate;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
