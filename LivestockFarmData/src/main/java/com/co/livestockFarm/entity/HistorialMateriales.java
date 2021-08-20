package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class HistorialMateriales {
	private int id;
	private Materiales materialesId;
	private String observacion;
	private Date fecha;
	private int entrada;
	private int salida;
	private int saldo;
}
