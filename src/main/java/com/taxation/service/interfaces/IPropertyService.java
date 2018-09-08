package com.taxation.service.interfaces;

import java.util.List;

import com.taxation.model.Property;
import com.taxation.resource.PayTaxRequest;

public interface IPropertyService {

	public List<Property> getAll();

	public void payTax(PayTaxRequest payTaxRequest) throws Exception;

	public void createProperty(Property property);
}
