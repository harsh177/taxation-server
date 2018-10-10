package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taxation.model.Property;

import java.util.List;

@Repository
public interface IPropertyDAO extends JpaRepository<Property,Integer> {
    @Query("select p from Property p where p.samagraId = ?1 and p.isActive = true")
    List<Property> findBySamagraId(String id);

    @Query("select p from Property p where p.customUniqueId = ?1 and p.isActive = true")
    List<Property> findByUniqueId(String id);

    @Query("select p from Property p where p.isActive = true")
    List<Property> getAllActiveProperties();
    
    @Query("select p from Property p where p.area = ?1 and p.isActive = true")
	List<Property> getAllActivePropertiesBasedOnArea(String area);
}
