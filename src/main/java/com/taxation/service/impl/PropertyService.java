package com.taxation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.taxation.dao.interfaces.ITaxDetailsDAO;
import com.taxation.model.*;
import com.taxation.resource.PayTaxRequest;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.ITaxDetailsService;
import com.taxation.service.interfaces.ITaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPropertyDAO;
import com.taxation.service.interfaces.IPropertyService;

@Service
public class PropertyService implements IPropertyService {
	@Autowired
	private IPropertyDAO iPropertyDAO;

	@Autowired
	private IPersonService personService;

	@Autowired
	private ITaxService iTaxService;

	@Autowired
	private ITaxDetailsService iTaxDetailsService;

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
		System.out.println(property);
		Person person = personService.getPersonBySamagraId(property.getSamagraId());

		property.setPerson(person);
		Property createdProperty = iPropertyDAO.save(property);
		System.out.println(createdProperty);
		TaxDetail taxDetail = new TaxDetail();
		if(createdProperty.getIsWaterConnected()){
		  Tax tax = iTaxService.getTaxForWaterConnectedProperty();
			System.out.println(tax);
		  taxDetail.setAmount(tax.getValue());

		}else {
			Tax tax = iTaxService.getTaxForWithoutWaterConnectionProperty();
			taxDetail.setAmount(tax.getValue());
		}
		taxDetail.setProperty(createdProperty);
		taxDetail.setCurrentTaxPaymentStatus("DUE");
		iTaxDetailsService.save(taxDetail);
	}

	@Override
	public List<Property> findBySamagraId(String samagraId) {
		return iPropertyDAO.findBySamagraId(samagraId);
	}

	@Override
	public List<Property> findByPhoneNumber(String phoneNumber) throws Exception {
		String samagraId = personService.getSamagraIdByPhoneNumber(phoneNumber);
		if(samagraId==null) throw new Exception("No property found for phone :"+phoneNumber);
		return findBySamagraId(samagraId);
	}


}
