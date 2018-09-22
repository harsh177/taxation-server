package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taxation.model.Property;

import java.util.List;

@Repository
public interface IPropertyDAO extends JpaRepository<Property,Integer> {
    @Query("select p from Property p where p.samagraId = ?1")
    List<Property> findBySamagraId(String id);

    @Query("select p from Property p where p.customUniqueId = ?1")
    List<Property> findByUniqueId(String id);
}
