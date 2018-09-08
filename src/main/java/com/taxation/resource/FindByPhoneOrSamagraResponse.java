package com.taxation.resource;

import com.taxation.model.Person;
import com.taxation.model.Property;

import java.util.List;

public class FindByPhoneOrSamagraResponse {
    private Person person;
    private List<Property> propertyList;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
