package com.taxation.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.taxation.dao.interfaces.ITaxDetailsDAO;
import com.taxation.dao.interfaces.UserRepository;
import com.taxation.model.*;
import com.taxation.resource.PayTaxRequest;
import com.taxation.resource.TransferPropertyRequest;
import com.taxation.service.interfaces.IPanchayatService;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.ITaxDetailsService;
import com.taxation.service.interfaces.ITaxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IDocumentDAO;
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

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	@Qualifier("panchayatServiceProvider")
	IPanchayatService iPanchayatService;

	@Autowired
	IDocumentDAO	iDocumentDAO;
	
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
	public void createProperty(Property property,Long	pid,Long	uid) throws Exception {
		if(!iPanchayatService.getPanchayatById(pid).isPresent())throw new Exception("Panchayat	not	found :" + pid);
		Panchayat	panchayat=iPanchayatService.getPanchayatById(pid).get();
		property.setPanchayat(panchayat);
		if(!userRepository.findById(uid).isPresent())throw new Exception("User	not	found :" + uid);
		User	user=userRepository.findById(uid).get();
		property.setUser(user);
		Person person = personService.getPersonBySamagraId(property.getSamagraId());
		if(person==null) throw new Exception("Invalid Samagra Id :" + property.getSamagraId());
		property.setPerson(person);
		List<Document>	docList	=	iDocumentDAO.saveAll(property.getDocuments());
		property.setDocuments(docList);
		property.setCustomUniqueId(generateUUID());
		property.setActive(true);
		Property createdProperty = iPropertyDAO.save(property);

		TaxDetail taxDetail = new TaxDetail();
		if(createdProperty.getIsWaterConnected()){
		  Tax tax = iTaxService.getTaxForWaterConnectedProperty();
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
		List<Property> properties= null;
		if(personService.getPersonBySamagraId(samagraId)!=null	&&	personService.getPersonBySamagraId(samagraId).getName()!=null){
			properties	=	iPropertyDAO.findBySamagraId(samagraId);
		}else{
			//this	is	delete	case
			throw new Exception("No property found for samgraId :"+samagraId);
		}
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

	@Override
	public List<Property> findByUniqueId(String uniqueId) {
		// TODO Auto-generated method stub
		List<Property> properties= iPropertyDAO.findByUniqueId(uniqueId);
		return properties;
	}

	@Override
	public void transferProperty(TransferPropertyRequest transferPropertyRequest,Long pid,Long uid) throws Exception {
		//fetch property by property id make it inactive and save
		Property property = getById(transferPropertyRequest.getPropertyId());
		Property newProperty = new Property();
		if(property.getActive()) {
			property.setTransferred(true);
			property.setTransferredToSamagraId(transferPropertyRequest.getTransferToSamagraId());
			iPropertyDAO.save(property);
		}else {
			throw new Exception("Property with Unique id :"+property.getCustomUniqueId()+"does not belong to current samagra and cant be transffered");
		}
		//fetch the person - the new owner of this property
		newProperty.setActive(true);
		newProperty.setArea(property.getArea());
		newProperty.setIsResidential(property.getIsResidential());
		newProperty.setResidentName(property.getResidentName());
		newProperty.setResidentName(transferPropertyRequest.getResidentName());
		newProperty.setSubHolder(transferPropertyRequest.getNewSubHolder());
		newProperty.setIsWaterConnected(property.getWaterConnected());
		newProperty.setEastLandmark(property.getEastLandmark());
		newProperty.setWestLandmark(property.getWestLandmark());
		newProperty.setNorthLandmark(property.getNorthLandmark());
		newProperty.setSouthLandmark(property.getSouthLandmark());
		newProperty.setVillage(property.getVillage());
		newProperty.setTehsil(property.getTehsil());
		newProperty.setDistrict(property.getDistrict());
		newProperty.setPropertyNumber(property.getPropertyNumber());
		newProperty.setPanchayat(property.getPanchayat());
		newProperty.setSharedWallDescription(property.getSharedWallDescription());
		newProperty.setWaterBillDescription(property.getWaterBillDescription());
		newProperty.setLength(property.getLength());
		newProperty.setWidth(property.getWidth());
        newProperty.setCustomUniqueId(property.getCustomUniqueId());
		BeanUtils.copyProperties(property.getPropertyUsages(),newProperty.getPropertyUsages());
		BeanUtils.copyProperties(property.getPropertyTypes(),newProperty.getPropertyTypes());
//		newProperty.setPropertyUsages(property.getPropertyUsages());
		newProperty.setOtherDescription(property.getOtherDescription());
		newProperty.setPincode(property.getPincode());
		//the updated field
		newProperty.setSamagraId(transferPropertyRequest.getTransferToSamagraId());
		newProperty.setDocuments(transferPropertyRequest.getDocuments());
		createTransferredProperty(newProperty,pid,uid);
	}

	@Override
	public void updateProperty(Property property,Long pid, Long uid) throws Exception {
		if(!iPanchayatService.getPanchayatById(pid).isPresent())throw new Exception("Panchayat	not	found :" + pid);
		Panchayat	panchayat=iPanchayatService.getPanchayatById(pid).get();
		property.setPanchayat(panchayat);
		if(!userRepository.findById(uid).isPresent())throw new Exception("User	not	found :" + uid);
		User	user=userRepository.findById(uid).get();
		property.setUser(user);
		Person person = personService.getPersonBySamagraId(property.getSamagraId());
		if(person==null) throw new Exception("Invalid Samagra Id :" + property.getSamagraId());
		property.setPerson(person);
		List<Document>	docList	=	iDocumentDAO.saveAll(property.getDocuments());
		property.setDocuments(docList);
		TaxDetail taxDetail = new TaxDetail();
		List<TaxDetail> taxDetails = iTaxDetailsService.getTaxDetailsByPropertyId(property);
		System.out.println(taxDetails.size());
		for (TaxDetail td: taxDetails) {
			if(td.getCreatedAt().getMonth().getValue() == LocalDateTime.now().getMonth().getValue()){
				if(property.getIsWaterConnected()){
					System.out.println("IN IFFFFFF");
					Tax tax = iTaxService.getTaxForWaterConnectedProperty();
					td.setAmount(tax.getValue());
					td.setCurrentTaxPaymentStatus(PaymentStatus.DUE);
					iTaxDetailsService.save(td);
				}else {
					System.out.println("IN ELSE");
					Tax tax = iTaxService.getTaxForWithoutWaterConnectionProperty();
					td.setAmount(tax.getValue());
					td.setCurrentTaxPaymentStatus(PaymentStatus.DUE);
					iTaxDetailsService.save(td);
				}
				break;
				}
			}
		iPropertyDAO.save(property);
	}

	@Override
	public void deleteProperty(Integer propertyId) throws Exception {
		Property property = iPropertyDAO.findById(propertyId).get();
		property.setActive(false);
		iPropertyDAO.save(property);
	}

	@Override
	public List<Property> getAllActiveProperties() {
	 return	iPropertyDAO.getAllActiveProperties();
	}

	private	String	generateUUID(){
		//waah kya jugaadu kaam hai
		return	UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	@Override
	public void createTransferredProperty(Property property,Long	pid,Long	uid) throws Exception {
		if(!iPanchayatService.getPanchayatById(pid).isPresent())throw new Exception("Panchayat	not	found :" + pid);
		Panchayat	panchayat=iPanchayatService.getPanchayatById(pid).get();
		property.setPanchayat(panchayat);
		if(!userRepository.findById(uid).isPresent())throw new Exception("User	not	found :" + uid);
		User	user=userRepository.findById(uid).get();
		property.setUser(user);
		Person person = personService.getPersonBySamagraId(property.getSamagraId());
		if(person==null) throw new Exception("Invalid Samagra Id :" + property.getSamagraId());
		property.setPerson(person);
		List<Document>	docList	=	iDocumentDAO.saveAll(property.getDocuments());
		property.setDocuments(docList);
		property.setActive(true);
		Property createdProperty = iPropertyDAO.save(property);

		TaxDetail taxDetail = new TaxDetail();
		if(createdProperty.getIsWaterConnected()){
			Tax tax = iTaxService.getTaxForWaterConnectedProperty();
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
	public List<Property> getAllActivePropertiesBasedOnArea(String area) {
		
		return iPropertyDAO.getAllActivePropertiesBasedOnArea(area);
	}

}
