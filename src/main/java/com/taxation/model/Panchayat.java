package com.taxation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "panchayat")
public class Panchayat{

    @Id
    @GeneratedValue
    @Column(name = "panchayat_id")
    private Long panchayatId;

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
    
    @Column(length = 200)
    @NotNull
    @NotBlank    
    private String address;
    
    @Lob
    private String logo;
    
    
    public Panchayat() {

    }

	public Panchayat(
			String name,
			String head, String district,String address, String logo) {
		super();
		this.name = name;
		this.head = head;
		this.district = district;
		this.address = address;
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public Long getPanchayatId() {
        return panchayatId;
    }

    public void setPanchayatId(Long panchayatId) {
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
