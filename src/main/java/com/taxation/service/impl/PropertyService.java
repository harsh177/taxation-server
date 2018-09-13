package com.taxation.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
	public void payTax(List<TaxDetail> taxDetails) {
		List<TaxDetail> updatedTaxDetails = new ArrayList<>();
		for(TaxDetail taxDetail: taxDetails){
			taxDetail.setLastTaxPaidOn(new Date());
			taxDetail.setCurrentTaxPaymentStatus(PaymentStatus.PAID);
			updatedTaxDetails.add(taxDetail);
		}
		iTaxDetailsService.saveAll(taxDetails);
	}

	@Override
	public void createProperty(Property property) throws Exception {
		Person person = personService.getPersonBySamagraId(property.getSamagraId());
		if(person==null) throw new Exception("Invalid Samagra Id :" + property.getSamagraId());
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
		taxDetail.setCurrentTaxPaymentStatus(PaymentStatus.DUE);
		iTaxDetailsService.save(taxDetail);
	}

	@Override
	public List<Property> findBySamagraId(String samagraId) throws Exception {
		List<Property> properties= iPropertyDAO.findBySamagraId(samagraId);
		//if(properties.size()==0) throw new Exception("No property found for samgraId :"+samagraId);
		return properties;
	}

	@Override
	public List<Property> findByPhoneNumber(String phoneNumber) throws Exception {
		String samagraId = personService.getSamagraIdByPhoneNumber(phoneNumber);
		if(samagraId==null) throw new Exception("No property found for phone :"+phoneNumber);
		return findBySamagraId(samagraId);
	}

	@Override
	public Property getById(Integer propertyId) {
		Property property = null;
		try{
			property = iPropertyDAO.findById(propertyId).get();
		}catch(Exception nee){
			throw  new NoSuchElementException("No property found with ID :"+propertyId);
		}
		return  property;
	}


}
