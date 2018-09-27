package com.taxation.service.impl;

import com.taxation.model.PaymentStatus;
import com.taxation.model.Property;
import com.taxation.model.Tax;
import com.taxation.model.TaxDetail;
import com.taxation.service.interfaces.IPropertyService;
import com.taxation.service.interfaces.IScheduledServices;
import com.taxation.service.interfaces.ITaxDetailsService;
import com.taxation.service.interfaces.ITaxService;
import org.springframework.beans.factory.annotation.Autowired;

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

    private Tax taxForPropertyWithWaterConnection = iTaxService.getTaxForWaterConnectedProperty();
    private Tax taxForPropertyWithoutWaterConnection = iTaxService.getTaxForWithoutWaterConnectionProperty();



    @Override
    public void createTaxDetailsForAllActivePropertiesForThisMonth() {
        //Date todaysDate = new Date();
        //if(todaysDate) a check for checking whether is first date only or not
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
