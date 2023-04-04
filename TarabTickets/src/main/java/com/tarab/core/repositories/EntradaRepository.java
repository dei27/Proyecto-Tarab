package com.tarab.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarab.core.model.Entrada;
import com.tarab.core.model.Evento;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

	public List<Entrada> findByEvento(Evento evento);
}
