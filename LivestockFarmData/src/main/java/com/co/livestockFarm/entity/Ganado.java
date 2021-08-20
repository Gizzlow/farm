package com.co.livestockFarm.entity;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Ganado {
	private int id;
	private String nombre;
	private Ganado madreId;
	private String tipo;
	private boolean activo;
	private String observacion;
}
