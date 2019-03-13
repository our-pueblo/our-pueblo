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

    @Column
    private String untranslated_text;

    @Column
    private String description;

    @OneToOne
    @JsonManagedReference
    private Department department_id;

    @OneToOne
    @JsonManagedReference
    private User user_id;

    @Column
    private String google_translate;

    @OneToOne
    @JsonManagedReference
    private Request_Status status;


    //TODO Ask about this
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date createDate;




}
