package com.taxation.dao.interfaces;

import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITaxDetailsDAO extends JpaRepository<TaxDetail, Integer> {

    @Query("select td from TaxDetail td where td.property = ?1")
    public List<TaxDetail> getTaxDetailsByPropertyId(Property property);

    @Query("select td from TaxDetail td where td.currentTaxPaymentStatus = 'DUE'")
    public List<TaxDetail> getAllDueTaxDetails();

    @Query("select td from TaxDetail td where td.currentTaxPaymentStatus = 'PAID'")
    public List<TaxDetail> getAllPaidTaxDetails();

}
