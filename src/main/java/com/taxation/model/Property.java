package com.taxation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="property")
public class Property  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "property_id")
	private Integer propertyId;
	
	@Column(name = "property_number",length = 10)
	private String propertyNumber;

	@Column(length = 100)
	private String area;
	
	@Column(length = 50)
	private String city;
	
	@Column(length = 8)
	private String pincode;

	@Column(name = "samagra_id",length = 9)
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
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@OneToMany
	@JoinTable(name="property_usage_mapping",joinColumns=@JoinColumn(name="property_id"),
	inverseJoinColumns=@JoinColumn(name="property_usage_id"))
	private Collection<PropertyUsage> propertyUsage = new ArrayList<>();
	
	@OneToMany
	@JoinTable(name="property_type_mapping",joinColumns=@JoinColumn(name="property_id"),
	inverseJoinColumns=@JoinColumn(name="property_type_id"))
	private Collection<PropertyType> propertyType = new ArrayList<>();
	

	public Collection<PropertyUsage> getPropertyUsage() {
		return propertyUsage;
	}

	public void setPropertyUsage(Collection<PropertyUsage> propertyUsage) {
		this.propertyUsage = propertyUsage;
	}

	public Collection<PropertyType> getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Collection<PropertyType> propertyType) {
		this.propertyType = propertyType;
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

	@Override
	public String toString() {
		return "Property{" +
				"propertyId=" + propertyId +
				", propertyNumber='" + propertyNumber + '\'' +
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
				", person=" + person +
				", propertyUsage=" + propertyUsage +
				", propertyType=" + propertyType +
				'}';
	}
}
