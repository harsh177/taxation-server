package com.taxation.service.interfaces;

import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import java.util.List;

public interface ITaxDetailsService {

    public void save(TaxDetail taxDetail);
    public List<TaxDetail> saveAll(List<TaxDetail> taxDetails);

    public List<TaxDetail> getTaxDetailsByPropertyId(Property property);

}
