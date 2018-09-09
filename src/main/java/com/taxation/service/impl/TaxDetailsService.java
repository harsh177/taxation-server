package com.taxation.service.impl;

import com.taxation.dao.interfaces.ITaxDetailsDAO;
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
    public void saveAll(List<TaxDetail> taxDetails) {
        iTaxDetailsDAO.saveAll(taxDetails);
    }
}
