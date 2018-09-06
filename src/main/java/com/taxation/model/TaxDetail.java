package com.taxation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tax_detail")
public class TaxDetail  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tax_detail_id")
	private Integer	taxDetailId;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@Column(name = "last_tax_paid_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastTaxPaidOn; 
	
	private String currentTaxPaymentStatus;
	
	private Integer amount;
	
	public Integer getTaxDetailId() {
		return taxDetailId;
	}
	public void setTaxDetailId(Integer taxDetailId) {
		this.taxDetailId = taxDetailId;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public Date getLastTaxPaidOn() {
		return lastTaxPaidOn;
	}
	public void setLastTaxPaidOn(Date lastTaxPaidOn) {
		this.lastTaxPaidOn = lastTaxPaidOn;
	}
	public String getCurrentTaxPaymentStatus() {
		return currentTaxPaymentStatus;
	}
	public void setCurrentTaxPaymentStatus(String currentTaxPaymentStatus) {
		this.currentTaxPaymentStatus = currentTaxPaymentStatus;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
