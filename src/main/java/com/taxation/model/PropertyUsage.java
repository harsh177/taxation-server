package com.taxation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="property_usage")
public class PropertyUsage  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "property_usage_id")
	private Integer propertyUsageId;
	
	@Column(length = 20)
	private String name;

	public Integer getPropertyUsageId() {
		return propertyUsageId;
	}

	public void setPropertyUsageId(Integer propertyUsageId) {
		this.propertyUsageId = propertyUsageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}