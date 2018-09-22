package com.taxation.service.interfaces;

import java.util.List;

import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import com.taxation.resource.PayTaxRequest;

public interface IPropertyService {

	public List<Property> getAll();

	public void payTax(List<TaxDetail> taxDetails) throws Exception;

	public List<Property> findBySamagraId(String samagraId) throws Exception;

	public List<Property> findByPhoneNumber(String phoneNumber) throws Exception;

	public Property getById(Integer propertyId);

	void createProperty(Property property, Long pid, Long uid) throws Exception;

	public List<Property> findByUniqueId(String uniqueId);
}
