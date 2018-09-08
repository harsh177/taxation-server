package com.taxation.dao.interfaces;

import com.taxation.model.PropertyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPropertyUsageDao extends JpaRepository<PropertyUsage, Integer> {

}
