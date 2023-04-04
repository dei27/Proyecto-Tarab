package com.tarab.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lugares")
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lugar")
    private Integer idLugar;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    public Lugar() {}

	public Lugar(String nombre, String direccion, Pais pais) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.pais = pais;
	}

	public Integer getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(Integer idLugar) {
		this.idLugar = idLugar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
    
    
}
