package com.tarab.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarab.core.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
