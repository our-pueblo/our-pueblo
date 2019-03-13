package com.codeup.ourpueblo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_status")
public class Request_Status {

    @Id
    private long id;

    @Column
    private String status;

    public Request_Status(){}

    public Request_Status(long id, String status){
        this.id = id;
        this.status = status;
    }

    public Request_Status(String status){
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
