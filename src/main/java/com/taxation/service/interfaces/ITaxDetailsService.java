package com.taxation.service.interfaces;

import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface ITaxDetailsService {

    public void save(TaxDetail taxDetail);
    public List<TaxDetail> saveAll(List<TaxDetail> taxDetails);

    public List<TaxDetail> getTaxDetailsByPropertyId(Property property);
	public TaxDetail getById(Integer taxDetailId);
    public List<TaxDetail> getAllDueTaxDetails();

    public List<TaxDetail> getAllPaidTaxDetails();

    public void createTaxDetailsForAllActivePropertiesForThisMonth() throws Exception;

}
