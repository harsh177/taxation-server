package com.taxation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="property_type")
public class PropertyType  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "property_type_id")
	private Integer propertyTypeId;
	
	@Column(length = 20,unique = true)
	private String name;

	public Integer getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(Integer propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyType(String name) {
		this.name = name;
	}

	public PropertyType(){

	}
}