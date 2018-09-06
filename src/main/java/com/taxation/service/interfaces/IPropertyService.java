package com.taxation.service.interfaces;

import java.util.List;

import com.taxation.model.Property;

public interface IPropertyService {
	public void save(Property property);

	public List<Property> getAll();
		
}
