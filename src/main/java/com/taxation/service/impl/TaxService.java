package com.taxation.service.impl;

import com.taxation.dao.interfaces.ITaxDao;
import com.taxation.model.Tax;
import com.taxation.service.interfaces.ITaxService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaxService  implements ITaxService{

    @Autowired
    private ITaxDao iTaxDao;

    @Override
    public void saveAll(List<Tax> taxList) {
        iTaxDao.saveAll(taxList);
    }
}
