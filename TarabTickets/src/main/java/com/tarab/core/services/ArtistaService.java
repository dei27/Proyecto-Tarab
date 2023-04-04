package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Artista;
import com.tarab.core.repositories.ArtistaRepository;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepo;

	public List<Artista> obtenerTodos() {
		return artistaRepo.findAll();
	}

	public Artista obtenerUno(Integer idArtista) {
		return artistaRepo.findById(idArtista).orElseThrow(null);
	}

	public void nuevoArtista(Artista artista) {
		artistaRepo.save(artista);
	}
}
