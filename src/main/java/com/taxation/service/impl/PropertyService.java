package com.taxation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;
import com.taxation.resource.PayTaxRequest;
import com.taxation.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPropertyDAO;
import com.taxation.model.Property;
import com.taxation.service.interfaces.IPropertyService;

@Service
public class PropertyService implements IPropertyService {
	@Autowired
	private IPropertyDAO iPropertyDAO;

	private IPersonService personService;
	


	@Override
	public List<Property> getAll() {
		return iPropertyDAO.findAll();
	}

	@Override
	public void payTax(PayTaxRequest payTaxRequest) throws Exception {
		Integer propertyId = payTaxRequest.getPropertyId();
		try {
			Property property = iPropertyDAO.findById(propertyId).get();
			System.out.println(propertyId);
			System.out.println(property);
		}catch (NoSuchElementException e){
			throw new NoSuchElementException("Invalid property ID");
		}
	}

	@Override
	public void createProperty(Property property) {
		List<PropertyUsage> propertyUsages = new ArrayList<>();
		List<PropertyType> propertyTypes = new ArrayList<>();
		//fetch property usages
		//fetch property types
		//set into property object
		//save property
		iPropertyDAO.save(property);
	}

	@Override
	public List<Property> findBySamagraId(String samagraId) {
		return iPropertyDAO.findBySamagraId(samagraId);
	}

	@Override
	public List<Property> findByPhoneNumber(String phoneNumber) throws Exception {
		try {
			String samagraId = personService.getSamagraIdByPhoneNumber(phoneNumber);
			return findBySamagraId(samagraId);
		}catch (Exception e){
			throw new Exception("No member exists with phone number: "+phoneNumber);
		}
	}


}
