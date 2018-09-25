package com.taxation.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taxation.model.Person;

@Repository
public interface IPersonDAO extends JpaRepository<Person, Integer> {
	@Query("select e from Person e where e.samagraId = :samagraId")
	Person findBySamagraId(@Param("samagraId") String samagraId);

	@Query("select e from Person e where e.phone = ?1	and	e.active = true")
	Person findByPhoneNumber(String phone);

	@Query("select p.samagraId from Person p where p.phone = ?1	and	p.active = true")
	String getSamagraIdByPhoneNumber(String phone);

	@Query("select e from Person e where e.active = true")
	List<Person> getAllPersons();
}