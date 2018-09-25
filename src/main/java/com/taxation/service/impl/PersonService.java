package com.taxation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPersonDAO;
import com.taxation.model.Person;
import com.taxation.service.interfaces.IPersonService;

@Service
public class PersonService implements IPersonService {
	@Autowired
	private IPersonDAO iPersonDAO;
	
	@Override
	public void save(Person person) throws Exception {
		if(getPersonByPhoneNumber(person.getPhone())==null &&
				getPersonBySamagraId(person.getSamagraId()) == null){
			iPersonDAO.save(person);
		}else {
			throw new Exception("Samagra Id or Phone Already Exists");
		}
	}

	@Override
	public List<Person> getAll() {
		return iPersonDAO.getAllPersons();
	}

	@Override
	public Person getPersonBySamagraId(String id) {
		return iPersonDAO.findBySamagraId(id);
	}

	@Override
	public Person getPersonByPhoneNumber(String number) {
		// TODO Auto-generated method stub
		return iPersonDAO.findByPhoneNumber(number);
	}

	@Override
	public String getSamagraIdByPhoneNumber(String phoneNumber) {
		return iPersonDAO.getSamagraIdByPhoneNumber(phoneNumber);
	}

	@Override
	public Person getById(Integer id) {
		return iPersonDAO.findById(id).get();
	}

	@Override
	public void softDelete(Integer parseInt) {
		Person	p	=	getById(parseInt);
		p.setActive(false);
		iPersonDAO.save(p);
	}
	
	@Override
	public void edit(Person person) {
		iPersonDAO.save(person);
	}
}
