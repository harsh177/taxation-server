package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxation.model.Property;

@Repository
public interface IPropertyDAO extends JpaRepository<Property,Integer> {
	
}
