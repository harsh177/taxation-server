package com.taxation.service.interfaces;

import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;
import com.taxation.resource.SeedAllResponse;

import java.util.List;

public interface ISeedService {

    public SeedAllResponse seedAll();

    public List<PropertyType> getAllPropertyTypes();

    public List<PropertyUsage> getAllPropertyUsages();
}
