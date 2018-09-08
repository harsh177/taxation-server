package com.taxation.resource;

import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;
import com.taxation.model.Tax;

import java.util.List;

public class SeedAllResponse {

    private List<PropertyType> defaultPropertyTypes;
    private List<PropertyUsage> defaultPropertyUsages;
    private List<Tax> defaultTaxes;

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

    public List<Tax> getDefaultTaxes() {
        return defaultTaxes;
    }

    public void setDefaultTaxes(List<Tax> defaultTaxes) {
        this.defaultTaxes = defaultTaxes;
    }

    public SeedAllResponse(List<PropertyType> defaultPropertyTypes, List<PropertyUsage> defaultPropertyUsages, List<Tax> defaultTaxes) {
        this.defaultPropertyTypes = defaultPropertyTypes;
        this.defaultPropertyUsages = defaultPropertyUsages;
        this.defaultTaxes = defaultTaxes;
    }
}
