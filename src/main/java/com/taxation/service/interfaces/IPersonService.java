package com.taxation.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.taxation.model.Person;

public interface IPersonService {
	public void save(Person person) throws Exception;

	public List<Person> getAll();
	
	public Person getPersonBySamagraId(String id);
	
	public Person getPersonByPhoneNumber(String number);

	public String getSamagraIdByPhoneNumber(String phoneNumber);

	public	Person getById(Integer id);

	public void softDelete(Integer parseInt);

	void edit(Person person);
	
}
