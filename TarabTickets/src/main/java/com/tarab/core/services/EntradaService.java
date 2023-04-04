package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Entrada;
import com.tarab.core.model.Evento;
import com.tarab.core.repositories.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository entradaRepo;
	
	public List<Entrada> obtenerEntradaByEvento(Evento evento) {
		return entradaRepo.findByEvento(evento);
	}
	
	public Entrada obtenerUnaById(Integer id) {
		return entradaRepo.findById(id).orElseThrow(null);
	}
}
