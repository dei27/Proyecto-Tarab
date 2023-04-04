package com.tarab.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.DetallePedido;
import com.tarab.core.repositories.DetallePedidoRepository;

@Service
public class DetallePedidoService {

	@Autowired
	private DetallePedidoRepository detallePedidoRepo;
	
	public void guardarDetalle(DetallePedido detalle) {
		detallePedidoRepo.save(detalle);
	}
}
