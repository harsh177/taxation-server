package com.taxation.resource;

import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;

import java.util.List;

public class SeedAllResponse {

    private List<PropertyType> defaultPropertyTypes;
    private List<PropertyUsage> defaultPropertyUsages;

    public List<PropertyUsage> getDefaultPropertyUsages() {
        return defaultPropertyUsages;
    }

    public void setDefaultPropertyUsages(List<PropertyUsage> defaultPropertyUsages) {
        this.defaultPropertyUsages = defaultPropertyUsages;
    }

    public List<PropertyType> getDefaultPropertyTypes() {
        return defaultPropertyTypes;
    }

    public void setDefaultPropertyTypes(List<PropertyType> defaultPropertyTypes) {
        this.defaultPropertyTypes = defaultPropertyTypes;
    }

    public SeedAllResponse(List<PropertyType> defaultPropertyTypes, List<PropertyUsage> defaultPropertyUsages) {
        this.defaultPropertyTypes = defaultPropertyTypes;
        this.defaultPropertyUsages = defaultPropertyUsages;
    }
}
