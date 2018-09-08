package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taxation.model.Person;

@Repository
public interface IPersonDAO extends JpaRepository<Person,Integer> {
    @Query("select e from Person e where e.samagraId = ?1")
    Person findBySamagraId(String id);

    @Query("select e from Person e where e.phone = ?1")
    Person findByPhoneNumber(String phone);

    @Query("select p.samagraId from Person p where p.phone = ?1")
    String getSamagraIdByPhoneNumber(String phone);
}