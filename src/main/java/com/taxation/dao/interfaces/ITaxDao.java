package com.taxation.dao.interfaces;

import com.taxation.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITaxDao extends JpaRepository<Tax,Integer> {

    @Query("select t from Tax t where t.name ='WATER'")
    Tax getTaxForWaterConnectedProperty();

    @Query("select t from Tax t where t.name ='HOUSE'")
    Tax getTaxForWithoutWaterConnectionProperty();
}
