package com.co.livestockFarm.entity;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class InventarioMateriales {
	private int id;
	private Materiales materialesId;
	private int cantidad;
}
