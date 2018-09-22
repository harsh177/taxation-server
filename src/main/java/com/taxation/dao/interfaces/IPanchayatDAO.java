package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxation.model.Panchayat;

@Repository
public interface IPanchayatDAO extends JpaRepository<Panchayat,Long>  {

}
