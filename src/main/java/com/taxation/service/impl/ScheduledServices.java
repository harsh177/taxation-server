package com.taxation.service.impl;

import com.taxation.common.URLConstants;
import com.taxation.model.*;
import com.taxation.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledServices implements IScheduledServices{

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ITaxService iTaxService;


    @Autowired
    private ITaxDetailsService iTaxDetailsService;

    @Autowired
    private ISchedularAuditService iSchedularAuditService;





    @Override
    public void createTaxDetailsForAllActivePropertiesForThisMonth() throws Exception {
         Tax taxForPropertyWithWaterConnection = iTaxService.getTaxForWaterConnectedProperty();
         Tax taxForPropertyWithoutWaterConnection = iTaxService.getTaxForWithoutWaterConnectionProperty();

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
        Tax taxForPropertyWithWaterConnection = iTaxService.getTaxForWaterConnectedProperty();
        Tax taxForPropertyWithoutWaterConnection = iTaxService.getTaxForWithoutWaterConnectionProperty();
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
