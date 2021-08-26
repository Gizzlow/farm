package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class InventoryMaterials {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "materialsId")
	private Materials materialsId;
	private int amount;
}
