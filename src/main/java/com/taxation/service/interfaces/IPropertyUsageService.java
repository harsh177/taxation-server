package com.taxation.service.interfaces;

import com.taxation.model.PropertyUsage;

import java.util.List;

public interface IPropertyUsageService {
    public void saveAll(List<PropertyUsage> propertyUsageList);

    public List<PropertyUsage> getAllPropertyUsages();
    }

