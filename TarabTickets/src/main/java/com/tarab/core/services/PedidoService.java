package com.tarab.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarab.core.model.Pedido;
import com.tarab.core.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	public Pedido guardarPedido(Pedido pedido) {
		Pedido pedidoGuardado = pedidoRepo.save(pedido);
		return pedidoGuardado;
	}
	
	public Pedido obtenerPedidoById(Integer idPedido) {
		return pedidoRepo.findById(idPedido).orElseThrow(null);
	}
	
	public List<Object[]> obtenerPedidosPorUsuario(Long idUsuario) {
        return pedidoRepo.findPedidosPorUsuario(idUsuario);
    }
}
