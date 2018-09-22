package com.taxation.service.interfaces;

import java.util.Optional;

import com.taxation.model.Panchayat;

public interface IPanchayatService {
	public Optional<Panchayat> getPanchayatById(Long id);
	public void save(Panchayat p);
}
