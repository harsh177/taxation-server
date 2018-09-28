package com.taxation.service.impl;

import com.taxation.common.URLConstants;
import com.taxation.model.*;
import com.taxation.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduledServices implements IScheduledServices{

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ITaxService iTaxService;

    @Autowired
    private ITaxDetailsService iTaxDetailsService;

    @Autowired
    private ISchedularAuditService iSchedularAuditService;

    private Tax taxForPropertyWithWaterConnection = iTaxService.getTaxForWaterConnectedProperty();
    private Tax taxForPropertyWithoutWaterConnection = iTaxService.getTaxForWithoutWaterConnectionProperty();



    @Override
    public void createTaxDetailsForAllActivePropertiesForThisMonth() {
        SchedularAudit schedularAudit = iSchedularAuditService.getBySchedularName(URLConstants.PROPERTY_TAX_DETAILS_SCHEDULAR);
        if(schedularAudit == null){
            schedularAudit.setLastRanOn(new Date());
            schedularAudit.setNameOfSchedular(URLConstants.PROPERTY_TAX_DETAILS_SCHEDULAR);
            logicOfTaxDetailsCreationForAllProperties();
            iSchedularAuditService.createShcedularAuditRecord(schedularAudit);
        }else {
            Date lastRanAt = schedularAudit.getLastRanOn();
            Date currentDate = new Date();
            if(currentDate.getMonth()!= lastRanAt.getMonth()){
               logicOfTaxDetailsCreationForAllProperties();
               schedularAudit.setLastRanOn(new Date());
               iSchedularAuditService.createShcedularAuditRecord(schedularAudit);
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
        iTaxDetailsService.saveAll(taxDetailsOfThisMonth);
    }
}
