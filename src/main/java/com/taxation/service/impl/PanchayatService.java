package com.taxation.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPanchayatDAO;
import com.taxation.model.Panchayat;
import com.taxation.service.interfaces.IPanchayatService;

@Service
public class PanchayatService implements IPanchayatService {

	@Autowired
	private IPanchayatDAO iPanchayatDAO;

	public Optional<Panchayat> getPanchayatById(Long id) {
		return iPanchayatDAO.findById(id);
	}

	public void save(Panchayat p) {
		iPanchayatDAO.save(p);
	}

}
