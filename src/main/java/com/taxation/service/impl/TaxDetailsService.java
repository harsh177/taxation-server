package com.taxation.service.impl;

import com.taxation.dao.interfaces.ITaxDetailsDAO;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import com.taxation.service.interfaces.ITaxDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaxDetailsService implements ITaxDetailsService {

    @Autowired
    private ITaxDetailsDAO iTaxDetailsDAO;

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
}
