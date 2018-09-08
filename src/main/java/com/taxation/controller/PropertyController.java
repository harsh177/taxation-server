package com.taxation.controller;

import java.util.List;

import javax.validation.Valid;

import com.taxation.resource.PayTaxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taxation.common.ApplicationConstants;
import com.taxation.common.URLConstants;
import com.taxation.entity.ApplicationResponse;
import com.taxation.model.Property;
import com.taxation.service.interfaces.IPropertyService;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class PropertyController {

	@Autowired
	@Qualifier("propertyService")
	private IPropertyService propertyService;// If you are using interface and it has more then one implementation

	
	@RequestMapping(value = URLConstants.PROPERTY_ADD, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> addProperty(@Valid @RequestBody Property person) {
		propertyService.createProperty(person);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Added Successfully",true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PROPERTY_GET_ALL, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getAllProperties() {
		List<Property> properties = propertyService.getAll();
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(properties,true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PAY_TAX, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> payTax(@RequestBody PayTaxRequest paytaxRequest) throws Exception {
		if(paytaxRequest.getPropertyId() != null) {
			propertyService.payTax(paytaxRequest);
			return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("nothing",true,null), HttpStatus.OK);
		}else{
			return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(paytaxRequest,true,"PropertyId cannot be Empty"),HttpStatus.PRECONDITION_FAILED);
		}
	}
}
