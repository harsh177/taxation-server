package com.taxation.service.impl;

import com.taxation.dao.interfaces.IPropertyUsageDao;
import com.taxation.model.PropertyUsage;
import com.taxation.service.interfaces.IPropertyUsageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropertyUsageService implements IPropertyUsageService {

    @Autowired
    IPropertyUsageDao iPropertyUsageDao;

    @Override
    public void saveAll(List<PropertyUsage> propertyUsageList) {
        iPropertyUsageDao.saveAll(propertyUsageList);
    }
}
