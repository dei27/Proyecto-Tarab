package com.tarab.core.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tarab.core.model.DetallePedido;
import com.tarab.core.model.Entrada;
import com.tarab.core.model.Evento;
import com.tarab.core.model.Pedido;
import com.tarab.core.services.DetallePedidoService;
import com.tarab.core.services.EntradaService;
import com.tarab.core.services.EventoService;
import com.tarab.core.services.PedidoService;
import com.tarab.core.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private EntradaService entradaService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detalleService;
	
	private Evento evento;
	
	private Entrada entradaModel;
	
	private Pedido pedidoGuardado;
	
	private List<Entrada> entrada;
	
	
	@GetMapping()
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("homeUser");
		List<Evento> eventos = eventoService.obtenerEvenetos();
		model.addObject("eventos", eventos);
		return model;
	}
	
	@PostMapping("/precompra/{id}")
	public ModelAndView formEntrada(@PathVariable("id") Integer idEvento) {
		ModelAndView model = new ModelAndView("formCompra");
		evento = eventoService.obtenerUno(idEvento);
		entrada = entradaService.obtenerEntradaByEvento(evento);
		model.addObject("entradas", entrada);
		model.addObject("evento", evento);
		return model;
	}
	
	@PostMapping("/confirmarCompra")
	public ModelAndView comprarEntrada(@RequestParam("entrada") Integer idEntrada, @RequestParam("cantidad") BigDecimal cantidad) {
		ModelAndView model = new ModelAndView("facturaCompra");
		pedidoGuardado = pedidoService.guardarPedido( new Pedido(UserDetailsServiceImpl.usuario, LocalDateTime.now()));
		entradaModel = entradaService.obtenerUnaById(idEntrada);
		model.addObject("entrada", entradaModel);
		model.addObject("idEntrada", idEntrada);
		model.addObject("cantidad", cantidad);
		model.addObject("pedido", pedidoGuardado);
		return model;
	}
	
	@PostMapping("/facturar")
	public ModelAndView facturar(@RequestParam("idEntrada") Integer idEntrada, @RequestParam("pedido") Integer idPedido, @RequestParam("cantidad") int cantidad,  @RequestParam("total") BigDecimal total ) {
		ModelAndView model = new ModelAndView("redirect:/usuario");
		pedidoGuardado = pedidoService.obtenerPedidoById(idPedido);
		pedidoGuardado.setMontoTotal(total);
		entradaModel = entradaService.obtenerUnaById(idEntrada);
		detalleService.guardarDetalle(new DetallePedido(pedidoGuardado, entradaModel, cantidad));
		model.addObject("guardado");
		return model;
	}
	
	@GetMapping("/eventosUsuario")
    public ModelAndView obtenerPedidosPorUsuario() {
        ModelAndView model = new ModelAndView("eventosUsuario");
        List<Object[]> pedidos = pedidoService.obtenerPedidosPorUsuario(UserDetailsServiceImpl.usuario.getId());
        model.addObject("pedidos", pedidos);
        return model;
    }
	
	
	
}
