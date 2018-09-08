package com.taxation.service.interfaces;

import java.util.List;

import com.taxation.model.Person;

public interface IPersonService {
	public void save(Person person);

	public List<Person> getAll();
	
	public Person getPersonBySamagraId(String id);
	
	public Person getPersonByPhoneNumber(String number);

	public String getSamagraIdByPhoneNumber(String phoneNumber);
	
}
