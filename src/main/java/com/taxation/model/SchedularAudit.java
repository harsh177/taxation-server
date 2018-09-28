package com.taxation.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="schedular_audit")
public class SchedularAudit {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_of_schedular",unique = true)
    private String nameOfSchedular;

    @Column(name = "last_ran_on", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRanOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfSchedular() {
        return nameOfSchedular;
    }

    public void setNameOfSchedular(String nameOfSchedular) {
        this.nameOfSchedular = nameOfSchedular;
    }

    public Date getLastRanOn() {
        return lastRanOn;
    }

    public void setLastRanOn(Date lastRanOn) {
        this.lastRanOn = lastRanOn;
    }
}
