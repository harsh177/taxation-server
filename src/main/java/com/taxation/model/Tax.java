package com.taxation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tax")
public class Tax  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "tax_id")
	private Integer taxId;
	
	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private Float value;

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Tax( String name, Float value) {
		this.name = name;
		this.value = value;
	}
	public Tax(){}

	@Override
	public String toString() {
		return "Tax{" +
				"taxId=" + taxId +
				", name='" + name + '\'' +
				", value=" + value +
				'}';
	}
}