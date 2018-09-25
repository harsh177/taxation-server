package com.taxation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taxation.common.ApplicationConstants;
import com.taxation.common.URLConstants;
import com.taxation.entity.ApplicationResponse;
import com.taxation.model.Person;
import com.taxation.service.interfaces.IPersonService;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class PersonController {

	@Autowired
	@Qualifier("personService")
	private IPersonService personService;// If you are using interface and it has more then one implementation

	
	@RequestMapping(value = URLConstants.PERSON_ADD, method = RequestMethod.POST, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> addPerson(@Valid @RequestBody Person person) throws Exception {
		personService.save(person);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Added Successfully",true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PERSON_GET_ALL, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getAllPersons() {
		List<Person> persons = personService.getAll();
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(persons,true,null), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstants.PERSON_GET_SAMAGRA, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPersonBySamagraId(@PathVariable String samagraId) {
		Person person = personService.getPersonBySamagraId(samagraId);
		if(person==null){
			return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("No member found with this SAMAGRA ID",true,"Not Found"), HttpStatus.OK);
		}
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(person,true,"Member found"), HttpStatus.OK);
	}
	
	@RequestMapping(value = URLConstants.PERSON_GET_PHONE, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPersonByPhoneNumber(@PathVariable String phone) {
		Person person = personService.getPersonByPhoneNumber(phone);
		if(person==null)return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(person,true,"No member found with this Phone number"), HttpStatus.OK);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(person,true,"Member found"), HttpStatus.OK);
	}
	
	@RequestMapping(value = URLConstants.PERSON_GET_ID, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> getPersonById(@PathVariable String id) {
		Person person = personService.getById(Integer.parseInt(id));
		if(person==null)return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("No member found with Id:"+id,false,null), HttpStatus.OK);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(person,true,null), HttpStatus.OK);
	}
	
	@RequestMapping(value = URLConstants.PERSON_DELETE, method = RequestMethod.DELETE, produces = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> deletePerson(@PathVariable String id) {
		personService.softDelete(Integer.parseInt(id));
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Deleted	Successfully",true,null), HttpStatus.OK);
	}
	
	@RequestMapping(value = URLConstants.PERSON_UPDATE, method = RequestMethod.PUT, consumes = ApplicationConstants.APP_JSON)
	public ResponseEntity<ApplicationResponse> updatePerson(@Valid @RequestBody Person person) throws Exception {
		personService.edit(person);
		return new ResponseEntity<ApplicationResponse>(new ApplicationResponse("Updated Successfully",true,null), HttpStatus.OK);
	}
	
}
