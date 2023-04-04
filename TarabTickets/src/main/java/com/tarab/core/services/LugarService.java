package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Lugar;
import com.tarab.core.repositories.LugarRepository;

@Service
public class LugarService {

	@Autowired
	private LugarRepository lugarRepo;
	
	public List<Lugar> obtenerTodos(){
		return lugarRepo.findAll();
	}
	
	public Lugar obtenerUno(Integer idLugar) {
		return lugarRepo.findById(idLugar).orElseThrow(null);
	}
	
	public void nuevoLugar(Lugar lugar) {
		lugarRepo.save(lugar);
	}
}
