package com.taxation.service.interfaces;

import com.taxation.dao.interfaces.ITaxDetailsDAO;
import com.taxation.model.TaxDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ITaxDetailsService {

    public void save(TaxDetail taxDetail);
    public void saveAll(List<TaxDetail> taxDetails);
}
