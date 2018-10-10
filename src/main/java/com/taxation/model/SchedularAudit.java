package com.taxation.model;


import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime lastRanOn;

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

    public LocalDateTime getLastRanOn() {
        return lastRanOn;
    }

    public void setLastRanOn(LocalDateTime lastRanOn) {
        this.lastRanOn = lastRanOn;
    }
}
