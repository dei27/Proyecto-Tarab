package com.tarab.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tarab.core.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query(value = "SELECT DISTINCT p.fecha_hora as fechaCompra, p.monto_total as total, dp.cantidad, te.nombre as nombreEntrada, ev.nombre as nombreEvento, ev.fecha as fechaEvento, lg.nombre as lugarNombre "
            + "FROM pedidos p "
            + "JOIN detalles_pedidos dp ON p.id_pedido = dp.id_pedido "
            + "JOIN entradas e ON e.id_entrada = dp.id_entrada "
            + "JOIN tipos_entradas te ON te.id_tipo_entrada = e.id_tipo_entrada "
            + "JOIN eventos ev ON ev.id_evento = e.id_evento "
            + "JOIN lugares lg ON lg.id_lugar = ev.id_lugar "
            + "JOIN usuarios u ON u.id = p.id_usuario "
            + "WHERE u.id = :idUsuario", nativeQuery = true)
    List<Object[]> findPedidosPorUsuario(Long idUsuario);

}
