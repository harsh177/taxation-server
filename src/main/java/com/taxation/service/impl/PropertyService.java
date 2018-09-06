package com.taxation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPropertyDAO;
import com.taxation.model.Property;
import com.taxation.service.interfaces.IPropertyService;

@Service
public class PropertyService implements IPropertyService {
	@Autowired
	private IPropertyDAO iPropertyDAO;
	
	@Override
	public void save(Property property) {
		iPropertyDAO.save(property);
	}

	@Override
	public List<Property> getAll() {
		return iPropertyDAO.findAll();
	}
	
}
