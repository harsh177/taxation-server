package com.taxation.dao.interfaces;

import com.taxation.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxDao extends JpaRepository<Tax,Integer> {
}
