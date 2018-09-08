package com.taxation.service.interfaces;

import com.taxation.model.Person;
import com.taxation.model.PropertyType;

import java.util.List;

public interface IPropertyTypeService {
    public void saveAll(List<PropertyType> propertyTypes);
}
