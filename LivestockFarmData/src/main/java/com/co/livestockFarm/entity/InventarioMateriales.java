package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class InventarioMateriales {
	@Id
	private int id;
//	private Materiales materialesId;
	private int cantidad;
}
