package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Medicina {
	@Id
	private int id;
	private String nombre;
	private String codigoICA;
	private String equivalente;
	private String grupo;
	private String ingredienteActivo;
	private String unidadMedida;
	private String tipo;
	private int tiempoRetiro;
}
