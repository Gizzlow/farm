package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class HistorialMateriales {
	@Id
	private int id;
	
//	private Materiales materialesId;
	private String observacion;
	private Date fecha;
	private int entrada;
	private int salida;
	private int saldo;
}
