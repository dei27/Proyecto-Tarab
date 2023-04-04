package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Genero;
import com.tarab.core.repositories.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepo;
	
	public List<Genero> obtenerTodos(){
		return generoRepo.findAll(); 
	}
	
	public Genero obtenerUno(Integer idGenero) {
		return generoRepo.findById(idGenero).orElseThrow(null);
	}
}
