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

    @Column(nullable = false)
    private String translation;

    @Column
    private String description_of_changes;

    @Column (nullable = false)
    private boolean flag_problm;

    @Column
    private String reason;

    @OneToOne
    private Request request;

    @OneToOne
    private Translation_Status status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date time;

    public Translation(){}

    public Translation(User user, String translation, String description_of_changes, boolean flag_problm, String reason, Request request, Translation_Status status, Date time) {
        this.user = user;
        this.translation = translation;
        this.description_of_changes = description_of_changes;
        this.flag_problm = flag_problm;
        this.reason = reason;
        this.request = request;
        this.status = status;
        this.time = time;
    }
}
