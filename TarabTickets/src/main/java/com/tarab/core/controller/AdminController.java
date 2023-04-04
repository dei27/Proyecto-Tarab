package com.tarab.core.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tarab.core.model.Artista;
import com.tarab.core.model.Evento;
import com.tarab.core.model.Genero;
import com.tarab.core.model.Lugar;
import com.tarab.core.model.Pais;
import com.tarab.core.services.ArtistaService;
import com.tarab.core.services.EventoService;
import com.tarab.core.services.GeneroService;
import com.tarab.core.services.LugarService;
import com.tarab.core.services.PaisService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private PaisService paisService;

	@Autowired
	private LugarService lugarService;

	private List<Artista> artistas;
	private List<Genero> generos;
	private List<Pais> paises;
	private List<Lugar> lugares;
	
	private Artista artista;
	private Genero genero;
	private Pais pais;
	private Evento eventoModel;
	private Lugar lugar;
	

	@GetMapping()
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("homeAdmin");
		List<Evento> eventos = eventoService.obtenerEvenetos();
		artistas = artistaService.obtenerTodos();
		lugares = lugarService.obtenerTodos();
		model.addObject("eventos", eventos);
		model.addObject("artistas", artistas);
		model.addObject("lugares", lugares);
		return model;
	}

	@GetMapping("/formEvento")
	public ModelAndView formEvento() {
		ModelAndView model = new ModelAndView("formEvento");
		artistas = artistaService.obtenerTodos();
		generos = generoService.obtenerTodos();
		lugares = lugarService.obtenerTodos();
		paises = paisService.obtenerTodos();
		model.addObject("artistas", artistas);
		model.addObject("generos", generos);
		model.addObject("paises", paises);
		model.addObject("lugares", lugares);
		return model;
	}
	
	@PostMapping("/nuevoLugar")
	public ModelAndView nuevoLugar(@RequestParam("nombre") String nombre, @RequestParam("direccion") String direccion, @RequestParam("pais") Integer idPais) {
		ModelAndView model = new ModelAndView("redirect:/admin/formEvento");
		pais = paisService.obtenerUno(idPais);
		lugarService.nuevoLugar(new Lugar(nombre, direccion, pais));
		return model;
	}
	
	@PostMapping("/nuevoArtista")
	public ModelAndView nuevoArtista(@RequestParam("nombre") String nombre, @RequestParam("genero") Integer idGenero) {
		ModelAndView model = new ModelAndView("redirect:/admin/formEvento");
		genero = generoService.obtenerUno(idGenero);
		artistaService.nuevoArtista(new Artista(nombre, genero));
		return model;
	}

	@PostMapping("/nuevoEvento")
	public ModelAndView formEvento(@RequestParam("evento") String evento, @RequestParam("artista") Integer idArtista,
			@RequestParam("lugar") Integer idLugar, @RequestParam("fecha") LocalDate fecha,
			@RequestParam("hora") LocalTime hora) {
		ModelAndView model = new ModelAndView("redirect:/admin");
		artista = artistaService.obtenerUno(idArtista);
		lugar = lugarService.obtenerUno(idLugar);
		eventoService.guardarEvento(new Evento(evento, fecha, lugar, artista, hora));
		return model;
	}
	
	@PostMapping("/eliminar/{id}")
	public ModelAndView formEvento(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("redirect:/admin");
		eventoService.eliminarEvento(id);
		return model;
	}
	
	@PostMapping("/modificar/{id}")
	public ModelAndView modificarEvento(@PathVariable("id") Integer id, @RequestParam("lugar") Integer idLugar, @RequestParam("artista") Integer idArtista, @RequestParam("fecha") LocalDate fecha, @RequestParam("hora") LocalTime hora) {
		ModelAndView model = new ModelAndView("redirect:/admin");
		eventoModel = eventoService.obtenerUno(id);
		artista = artistaService.obtenerUno(idArtista);
		lugar = lugarService.obtenerUno(idLugar);
		eventoModel.setLugar(lugar);
		eventoModel.setArtista(artista);
		eventoModel.setFecha(fecha);
		eventoModel.setHora(hora);
		eventoService.guardarEvento(eventoModel);
		return model;
	}

}
