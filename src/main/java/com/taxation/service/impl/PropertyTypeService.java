package com.taxation.service.impl;


import com.taxation.dao.interfaces.IPropertyTypeDao;
import com.taxation.model.PropertyType;
import com.taxation.service.interfaces.IPropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropertyTypeService implements IPropertyTypeService {

    @Autowired
    private IPropertyTypeDao iPropertyTypeDao;

    @Override
    public void saveAll(List<PropertyType> propertyTypes) {
        iPropertyTypeDao.saveAll(propertyTypes);
    }

    @Override
    public List<PropertyType> getAllPropertyTypes() {
        return iPropertyTypeDao.findAll();
    }

}
