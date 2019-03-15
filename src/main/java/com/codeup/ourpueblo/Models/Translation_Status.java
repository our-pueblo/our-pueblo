package com.codeup.ourpueblo.Models;

import javax.persistence.*;

@Entity
@Table(name = "translation_status")
public class Translation_Status {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String status;

    public Translation_Status(){}

    public Translation_Status(String status){
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
