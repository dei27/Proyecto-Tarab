package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Evento;
import com.tarab.core.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepo;
	
	public List<Evento> obtenerEvenetos(){
		return eventoRepo.findAll();
	}
	
	public void guardarEvento(Evento evento) {
		eventoRepo.save(evento);
	}
	
	public void eliminarEvento(Integer idEvento) {
		eventoRepo.deleteById(idEvento);
	}
	
	public Evento obtenerUno(Integer idEvento) {
		return eventoRepo.findById(idEvento).orElseThrow(null);
	}
}
