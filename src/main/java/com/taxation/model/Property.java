package com.taxation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taxation.model.audit.Auditable;

@Entity
@Table(name = "property")
public class Property  extends Auditable<String>{

	@Id
	@GeneratedValue
	@Column(name = "property_id")
	private Integer propertyId;

	@Column(name = "property_number", length = 10)
	private String propertyNumber;

	@Column(name = "sub_holder", length = 50)
	private String subHolder;

	@Column(length = 100)
	private String area;

	@Column(length = 50)
	private String village;

	@Column(length = 50)
	private String tehsil;

	@Column(length = 50)
	private String district;

	@Column(length = 8)
	private String pincode;

	@Column(name = "samagra_id")
	@Length(min = 8, max = 8, message = "Length of Samagra Id must be 8 characters")
	private String samagraId;

	@Column(name = "is_residential")
	private Boolean isResidential;

	@Column(name = "resident_name", length = 50)
	private String residentName;

	@Column
	private Float length;

	@Column
	private Float width;

	@Column(name = "east_landmark", length = 50)
	private String eastLandmark;

	@Column(name = "west_landmark", length = 50)
	private String westLandmark;

	@Column(name = "north_landmark", length = 50)
	private String northLandmark;

	@Column(name = "south_landmark", length = 50)
	private String southLandmark;

	@Column(name = "shared_wall_description", length = 200)
	private String sharedWallDescription;

	@Column(name = "is_water_connected")
	private Boolean isWaterConnected;

	@Column(name = "water_bill_description", length = 200)
	private String waterBillDescription;

	@Column(name = "other_description", length = 200)
	private String otherDescription;

	@Column(name = "custom_unique_id")
	private String customUniqueId;

	@Column(name = "is_active")
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnore
	private Person person;

	@ManyToOne
	@JoinColumn(name = "panchayat_id")
	@JsonIgnore
	private Panchayat panchayat;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@Column(name = "is_transferred")
	private Boolean isTransferred = false;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	@ManyToMany
	@JoinTable(name = "property_usage_mapping", joinColumns = @JoinColumn(name = "property_id", unique = false) , inverseJoinColumns = @JoinColumn(name = "property_usage_id", unique = false) )
	private Collection<PropertyUsage> propertyUsages = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "property_type_mapping", joinColumns = @JoinColumn(name = "property_id", unique = false) , inverseJoinColumns = @JoinColumn(name = "property_type_id", unique = false) )
	private Collection<PropertyType> propertyTypes = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "property_document_mapping", joinColumns = @JoinColumn(name = "property_id", unique = false) , inverseJoinColumns = @JoinColumn(name = "document_id", unique = false) )
	private Collection<Document> documents = new ArrayList<>();


	@Column(name = "transfer_to_samagra")
	private String transferredToSamagraId;

	public String getTransferredToSamagraId() {
		return transferredToSamagraId;
	}

	public void setTransferredToSamagraId(String transferredToSamagraId) {
		this.transferredToSamagraId = transferredToSamagraId;
	}

	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public Boolean getTransferred() {
		return isTransferred;
	}

	public void setTransferred(Boolean transferred) {
		isTransferred = transferred;
	}

	public String getCustomUniqueId() {
		return customUniqueId;
	}

	public void setCustomUniqueId(String customUniqueId) {
		this.customUniqueId = customUniqueId;
	}

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
}
