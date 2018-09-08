package com.taxation.dao.interfaces;

import com.taxation.model.Property;
import com.taxation.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPropertyTypeDao extends JpaRepository<PropertyType,Integer> {
}
