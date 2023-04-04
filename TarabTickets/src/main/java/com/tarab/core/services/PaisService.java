package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Pais;
import com.tarab.core.repositories.PaisRepository;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepo;
	
	public List<Pais> obtenerTodos() {
		return paisRepo.findAll();
	}
	
	public Pais obtenerUno(Integer id) {
		return paisRepo.findById(id).orElseThrow(null);
	}
}
