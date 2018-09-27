package com.taxation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taxation.common.ApplicationConstants;
import com.taxation.common.URLConstants;
import com.taxation.entity.ApplicationResponse;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import com.taxation.resource.FindByPhoneOrSamagraOrUniqueRequest;
import com.taxation.resource.FindByPhoneOrSamagraRequest;
import com.taxation.resource.FindByPhoneOrSamagraResponse;
import com.taxation.resource.TransferPropertyRequest;
import com.taxation.security.CurrentUser;
import com.taxation.security.UserPrincipal;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.IPropertyService;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class PropertyController {

	@Autowired
	@Qualifier("propertyService")
	private IPropertyService propertyService;// If you are using interface and it has more then one implementation

	@Autowired
	@Qualifier("personService")
	private IPersonService personService;
	
	@RequestMapping(value = URLConstants.PROPERTY_ADD, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> addProperty(@Valid @RequestBody Property property,@PathVariable Long pid,@PathVariable Long uid) throws Exception {
		propertyService.createProperty(property,pid,uid);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Added Successfully",true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_GET_ALL, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getAllProperties() {
		List<Property> properties = propertyService.getAll();
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(properties,true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PAY_TAX, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> payTax(@RequestBody List<TaxDetail> taxDetails) throws Exception {
		propertyService.payTax(taxDetails);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("PAID",true,"Payment Sucessful"), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_GET_BY_PHONE_OR_SAMAGRA, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> payTax(@RequestBody FindByPhoneOrSamagraRequest findByPhoneOrSamagraRequest) throws Exception {
		FindByPhoneOrSamagraResponse findByPhoneOrSamagraResponse= new FindByPhoneOrSamagraResponse();
		String phoneNumber = findByPhoneOrSamagraRequest.getPhoneNumber();
		String samagraId = findByPhoneOrSamagraRequest.getSamagraId();
		if(phoneNumber != null && !phoneNumber.isEmpty()){
			findByPhoneOrSamagraResponse.setPerson(personService.getPersonByPhoneNumber(phoneNumber));
			findByPhoneOrSamagraResponse.setPropertyList(propertyService.findByPhoneNumber(phoneNumber));
		}else if (samagraId !=null && !samagraId.isEmpty()){
			findByPhoneOrSamagraResponse.setPerson(personService.getPersonBySamagraId(samagraId));
			findByPhoneOrSamagraResponse.setPropertyList(propertyService.findBySamagraId(samagraId));
		}
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(findByPhoneOrSamagraResponse,true,"Person and Related Properties Fetched"), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_GET_BY_PHONE_OR_SAMAGRA_OR_UNIQUE, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPropertyByPhoneOrSamagraOrUniqueId(@RequestBody FindByPhoneOrSamagraOrUniqueRequest findByPhoneOrSamagraRequest) throws Exception {
		String phoneNumber = findByPhoneOrSamagraRequest.getPhoneNumber();
		String samagraId = findByPhoneOrSamagraRequest.getSamagraId();
		String uniqueId = findByPhoneOrSamagraRequest.getCustomUniqueId();
		List<Property>	properties	=	null;
		if(phoneNumber != null && !phoneNumber.isEmpty()){
			properties	=	propertyService.findByPhoneNumber(phoneNumber);
		}else if (samagraId !=null && !samagraId.isEmpty()){
			properties	=	propertyService.findBySamagraId(samagraId);
		}else	if(uniqueId !=null && !uniqueId.isEmpty()){
			properties	=	propertyService.findByUniqueId(uniqueId);
		}
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(properties,true,"Person and Related Properties Fetched"), HttpStatus.OK);
	}


	@RequestMapping(value = URLConstants.PROPERTY_GET_BY_PHONE, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPropertyByPhoneNumber(@PathVariable String phoneNumber) throws Exception {
		List<Property> properties = propertyService.findByPhoneNumber(phoneNumber);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(properties,true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_GET_BY_SAMAGRA, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPropertyBySamagra(@PathVariable String samagraId) throws Exception {
		List<Property> properties = propertyService.findBySamagraId(samagraId);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(properties,true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_TRANSFER, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> transferProperty(@RequestBody TransferPropertyRequest transferPropertyRequest,@PathVariable Long pid,@PathVariable Long uid) throws Exception {
		System.out.println(transferPropertyRequest);
		propertyService.transferProperty(transferPropertyRequest,pid,uid);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Transferred Successfully",true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_UPDATE, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> addProperty(@Valid @RequestBody Property property,@CurrentUser UserPrincipal currentUser) throws Exception {
		propertyService.updateProperty(property,currentUser.getPanchayat().getPanchayatId(),currentUser.getId());
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Updated Successfully",true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_DELETE, method = RequestMethod.DELETE, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> deleteProperty(@PathVariable Integer propertyId) throws Exception {

		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Property Deleted",true,null), HttpStatus.OK);
	}
}