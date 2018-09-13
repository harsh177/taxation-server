package com.taxation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="property")
public class Property  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "property_id")
	private Integer propertyId;
	
	@Column(name = "property_number",length = 10)
	private String propertyNumber;

	@Column(name = "sub_holder",length = 50)
	private String subHolder;

	@Column(length = 100)
	private String area;
	
	@Column(length = 50)
	private String city;
	
	@Column(length = 8)
	private String pincode;

	@Column(name = "samagra_id")
	@Length(min=8, max=8, message="Length of Samagra Id must be 8 characters")
	private String samagraId;
	
	@Column(name = "is_residential")	
	private Boolean isResidential;

	@Column(name = "resident_name",length = 50)
	private String residentName;
	
	@Column
	private Float length;
	
	@Column
	private Float width;
	
	@Column(name = "east_landmark",length = 50)	
	private String eastLandmark;
	
	@Column(name = "west_landmark",length = 50)	
	private String westLandmark;
	
	@Column(name = "north_landmark",length = 50)	
	private String northLandmark;
	
	@Column(name = "south_landmark",length = 50)	
	private String southLandmark;
	
	@Column(name = "shared_wall_description",length = 200)	
	private String sharedWallDescription;
	
	@Column(name = "is_water_connected")	
	private Boolean isWaterConnected;
	
	@Column(name = "water_bill_description",length = 200)	
	private String waterBillDescription;
	
	@Column(name = "other_description",length = 200)	
	private String otherDescription;

	public Boolean getResidential() {
		return isResidential;
	}

	public void setResidential(Boolean residential) {
		isResidential = residential;
	}



	public Boolean getWaterConnected() {
		return isWaterConnected;
	}

	public void setWaterConnected(Boolean waterConnected) {
		isWaterConnected = waterConnected;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	@Column(name = "is_active")
	private Boolean isActive;



	@ManyToOne
	@JoinColumn(name="person_id")
	@JsonIgnore
	private Person person;
	
	@ManyToMany
	@JoinTable(name="property_usage_mapping",joinColumns=@JoinColumn(name="property_id",unique = false),
	inverseJoinColumns=@JoinColumn(name="property_usage_id",unique = false))
	private Collection<PropertyUsage> propertyUsages = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="property_type_mapping",joinColumns=@JoinColumn(name="property_id",unique = false),
	inverseJoinColumns=@JoinColumn(name="property_type_id",unique = false))
	private Collection<PropertyType> propertyTypes = new ArrayList<>();
	
//	@CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at", nullable = false)
//    private Date createdAt;



//	@ManyToOne
//	@JoinColumn
////	@NotBlank
////	@NotNull
//	@JsonIgnore
//	private User createdBy;


	public Collection<PropertyUsage> getPropertyUsages() {
		return propertyUsages;
	}

	public void setPropertyUsages(Collection<PropertyUsage> propertyUsages) {
		this.propertyUsages = propertyUsages;
	}

	public Collection<PropertyType> getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(Collection<PropertyType> propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getSamagraId() {
		return samagraId;
	}

	public void setSamagraId(String samagraId) {
		this.samagraId = samagraId;
	}

	public Boolean getIsResidential() {
		return isResidential;
	}

	public void setIsResidential(Boolean isResidential) {
		this.isResidential = isResidential;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public String getEastLandmark() {
		return eastLandmark;
	}

	public void setEastLandmark(String eastLandmark) {
		this.eastLandmark = eastLandmark;
	}

	public String getWestLandmark() {
		return westLandmark;
	}

	public void setWestLandmark(String westLandmark) {
		this.westLandmark = westLandmark;
	}

	public String getNorthLandmark() {
		return northLandmark;
	}

	public void setNorthLandmark(String northLandmark) {
		this.northLandmark = northLandmark;
	}

	public String getSouthLandmark() {
		return southLandmark;
	}

	public void setSouthLandmark(String southLandmark) {
		this.southLandmark = southLandmark;
	}

	public String getSharedWallDescription() {
		return sharedWallDescription;
	}

	public void setSharedWallDescription(String sharedWallDescription) {
		this.sharedWallDescription = sharedWallDescription;
	}

	public Boolean getIsWaterConnected() {
		return isWaterConnected;
	}

	public void setIsWaterConnected(Boolean isWaterConnected) {
		this.isWaterConnected = isWaterConnected;
	}

	public String getWaterBillDescription() {
		return waterBillDescription;
	}

	public void setWaterBillDescription(String waterBillDescription) {
		this.waterBillDescription = waterBillDescription;
	}

	public String getOtherDescription() {
		return otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getSubHolder() {
		return subHolder;
	}

	public void setSubHolder(String subHolder) {
		this.subHolder = subHolder;
	}

//	public User getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(User createdBy) {
//		this.createdBy = createdBy;
//	}

	@Override
	public String toString() {
		return "Property{" +
				"propertyId=" + propertyId +
				", propertyNumber='" + propertyNumber + '\'' +
				", subHolder='" + subHolder + '\'' +
				", area='" + area + '\'' +
				", city='" + city + '\'' +
				", pincode='" + pincode + '\'' +
				", samagraId='" + samagraId + '\'' +
				", isResidential=" + isResidential +
				", residentName='" + residentName + '\'' +
				", length=" + length +
				", width=" + width +
				", eastLandmark='" + eastLandmark + '\'' +
				", westLandmark='" + westLandmark + '\'' +
				", northLandmark='" + northLandmark + '\'' +
				", southLandmark='" + southLandmark + '\'' +
				", sharedWallDescription='" + sharedWallDescription + '\'' +
				", isWaterConnected=" + isWaterConnected +
				", waterBillDescription='" + waterBillDescription + '\'' +
				", otherDescription='" + otherDescription + '\'' +
				", isActive=" + isActive +
				", person=" + person +
				", propertyUsages=" + propertyUsages +
				", propertyTypes=" + propertyTypes +
				'}';
	}
}
