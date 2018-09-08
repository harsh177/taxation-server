package com.taxation.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "panchayat")
public class Panchayat {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer panchayatId;

    @Column(length = 50)
    @NotBlank
    @NotNull
    @Size(min = 2, max = 50, message = "Name should have at least two charaters")
    private String name;

    @Column(length = 40)
    private String head;

    @Column(length = 30)
    @NotNull
    @NotBlank
    private String district;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPanchayatId() {
        return panchayatId;
    }

    public void setPanchayatId(Integer panchayatId) {
        this.panchayatId = panchayatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
