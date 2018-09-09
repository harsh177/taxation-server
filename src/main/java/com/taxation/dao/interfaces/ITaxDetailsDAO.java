package com.taxation.dao.interfaces;

import com.taxation.model.TaxDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxDetailsDAO extends JpaRepository<TaxDetail, Integer> {
}
