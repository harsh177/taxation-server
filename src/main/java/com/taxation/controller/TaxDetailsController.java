package com.taxation.controller;

import com.taxation.common.ApplicationConstants;
import com.taxation.common.URLConstants;
import com.taxation.entity.ApplicationResponse;
import com.taxation.model.Person;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import com.taxation.service.impl.ScheduledServices;
import com.taxation.service.impl.TaxDetailsService;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.IPropertyService;
import com.taxation.service.interfaces.IScheduledServices;
import com.taxation.service.interfaces.ITaxDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class TaxDetailsController {

    @Autowired
    IPropertyService propertyService;

    @Autowired
    ITaxDetailsService taxDetailsService;


    //IScheduledServices scheduledServices = new ScheduledServices();

    @RequestMapping(value = URLConstants.TAX_DETAILS_BY_PROPERTY, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
    public ResponseEntity<ApplicationResponse> getTaxDetailsByPropertyId(@PathVariable Integer propertyId) {
        Property property = propertyService.getById(propertyId);
        return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(taxDetailsService.getTaxDetailsByPropertyId(property),true,"Tax Details Fetched"), HttpStatus.OK);
    }
    
    @RequestMapping(value = URLConstants.TAX_DETAILS_BY_TAX_DETAIL, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
    public ResponseEntity<ApplicationResponse> getTaxDetailsByTaxDetailId(@PathVariable Integer taxDetailId) {
        TaxDetail taxDetail = taxDetailsService.getById(taxDetailId);
        return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(taxDetail,true,"Tax Details Fetched"), HttpStatus.OK);
    }

    @RequestMapping(value = URLConstants.CREATE_TAX_DETAILS_OF_ALL_PROPERTIES_FOR_MONTH, method = RequestMethod.GET, consumes = ApplicationConstants.APP_JSON)
    public ResponseEntity<ApplicationResponse> createTaxDetailsOfAllPropertiesForThisMonth() throws Exception {
        taxDetailsService.createTaxDetailsForAllActivePropertiesForThisMonth();
        return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("All Details created Successfully",true,null), HttpStatus.OK);
    }
}
