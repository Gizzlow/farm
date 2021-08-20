package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Tratamiento {
	private int id;
	private Ganado ganadoId;
	private Medicina medicinaId;
	private Date fecha;
	private Date fechaVencimiento;
	private String loteMedicina;
	private int cantidad;
	private String tratamiento;
	private Date finTratamiento;
	private String personaEncargada;
}
