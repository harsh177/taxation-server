package com.taxation.service.impl;

import com.taxation.common.URLConstants;
import com.taxation.dao.interfaces.ITaxDetailsDAO;
import com.taxation.model.*;
import com.taxation.service.interfaces.IPropertyService;
import com.taxation.service.interfaces.ISchedularAuditService;
import com.taxation.service.interfaces.ITaxDetailsService;
import com.taxation.service.interfaces.ITaxService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaxDetailsService implements ITaxDetailsService {

    @Autowired
    private ITaxDetailsDAO iTaxDetailsDAO;


    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ITaxService iTaxService;

    @Autowired
    private ISchedularAuditService iSchedularAuditService;

    private Tax taxForPropertyWithWaterConnection = iTaxService.getTaxForWaterConnectedProperty();
    private Tax taxForPropertyWithoutWaterConnection = iTaxService.getTaxForWithoutWaterConnectionProperty();

    @Override
    public void save(TaxDetail taxDetail) {
        iTaxDetailsDAO.save(taxDetail);
    }

    @Override
    public List<TaxDetail> saveAll(List<TaxDetail> taxDetails)  {
        return iTaxDetailsDAO.saveAll(taxDetails);
    }

    @Override
    public List<TaxDetail> getTaxDetailsByPropertyId(Property property) {
       return iTaxDetailsDAO.getTaxDetailsByPropertyId(property);
    }

	@Override
	public TaxDetail getById(Integer taxDetailId) {
		 return iTaxDetailsDAO.findById(taxDetailId).get();
	}

	public void createTaxDetailsForThisMonth(){

    }

	@Override
	public List<TaxDetail> getAllDueTaxDetails() {
		return iTaxDetailsDAO.getAllDueTaxDetails();
	}

	@Override
	public List<TaxDetail> getAllPaidTaxDetails() {
		return iTaxDetailsDAO.getAllPaidTaxDetails();
	}

    @Override
    public void createTaxDetailsForAllActivePropertiesForThisMonth() throws Exception {
        SchedularAudit schedularAudit = iSchedularAuditService.getBySchedularName(URLConstants.PROPERTY_TAX_DETAILS_SCHEDULAR);
        if(schedularAudit == null){
            schedularAudit.setLastRanOn(LocalDateTime.now());
            schedularAudit.setNameOfSchedular(URLConstants.PROPERTY_TAX_DETAILS_SCHEDULAR);
            logicOfTaxDetailsCreationForAllProperties();
            iSchedularAuditService.createShcedularAuditRecord(schedularAudit);
        }else {
            LocalDateTime lastRanAt = schedularAudit.getLastRanOn();
            LocalDateTime currentDate = LocalDateTime.now();
            // if(currentDate.getMonth().getValue()!= lastRanAt.getMonth().getValue()){
            if(currentDate.getDayOfMonth() != lastRanAt.getDayOfMonth()){
                logicOfTaxDetailsCreationForAllProperties();
                schedularAudit.setLastRanOn(currentDate);
                iSchedularAuditService.createShcedularAuditRecord(schedularAudit);
            }else{
                throw new Exception("This action can only be performed once in a month");
            }
        }
    }
    void logicOfTaxDetailsCreationForAllProperties(){
        List<Property> properties= propertyService.getAllActiveProperties();
        List<TaxDetail> taxDetailsOfThisMonth = new ArrayList<>();
        for (Property p : properties) {
            TaxDetail taxDetail = new TaxDetail();
            taxDetail.setCurrentTaxPaymentStatus(PaymentStatus.DUE);
            if(p.getIsWaterConnected()){
                taxDetail.setAmount(taxForPropertyWithWaterConnection.getValue());
            }else {
                taxDetail.setAmount(taxForPropertyWithoutWaterConnection.getValue());
            }
            taxDetail.setProperty(p);
            taxDetailsOfThisMonth.add(taxDetail);
        }
        saveAll(taxDetailsOfThisMonth);
    }

}
