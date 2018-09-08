package com.taxation.service.impl;

import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;
import com.taxation.resource.SeedAllResponse;
import com.taxation.service.interfaces.IPropertyTypeService;
import com.taxation.service.interfaces.IPropertyUsageService;
import com.taxation.service.interfaces.ISeedService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SeedService implements ISeedService {

    @Autowired
    IPropertyTypeService propertyTypeService;

    @Autowired
    IPropertyUsageService propertyUsageService;

    @Override
    public SeedAllResponse seedAll() {

        List<PropertyType> defaultPropertyTypes = new ArrayList<>();
        PropertyType propertyType1 = new PropertyType(1, "KAWELU");
        defaultPropertyTypes.add(propertyType1);
        PropertyType propertyType2 = new PropertyType(2, "MUD HOUSE");
        defaultPropertyTypes.add(propertyType2);
        PropertyType propertyType3 = new PropertyType(3, "HALF MUD HOUSE");
        defaultPropertyTypes.add(propertyType3);
        PropertyType propertyType4 = new PropertyType(4, "CONCRETE HOUSE");
        defaultPropertyTypes.add(propertyType4);
        propertyTypeService.saveAll(defaultPropertyTypes);


        List<PropertyUsage> defaultPropertyUsages = new ArrayList<>();
        PropertyUsage propertyUsage1 = new PropertyUsage(1,"FAMILY");
        defaultPropertyUsages.add(propertyUsage1);
        PropertyUsage propertyUsage2 = new PropertyUsage(2,"SHOP");
        defaultPropertyUsages.add(propertyUsage2);
        PropertyUsage propertyUsage3 = new PropertyUsage(3,"AGRICULTURE");
        defaultPropertyUsages.add(propertyUsage3);
        PropertyUsage propertyUsage4 = new PropertyUsage(4,"ANIMAL HUSBANDRY");
        defaultPropertyUsages.add(propertyUsage4);
        propertyUsageService.saveAll(defaultPropertyUsages);
        SeedAllResponse seedAllResponse = new SeedAllResponse(defaultPropertyTypes,defaultPropertyUsages);
        return seedAllResponse;
    }
}

//        --insert into property_usage values(1,'FAMILY');
//        --insert into property_usage values(2,'SHOP');
//        --insert into property_usage values(3,'AGRICULTURE');
//        --insert into property_usage values(4,'ANIMAL HUSBANDRY');

//--insert into property_usage values(1,'FAMILY');
//        --insert into property_usage values(2,'SHOP');
//        --insert into property_usage values(3,'AGRICULTURE');
//        --insert into property_usage values(4,'ANIMAL HUSBANDRY');