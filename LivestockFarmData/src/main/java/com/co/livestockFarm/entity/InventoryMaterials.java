package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class InventoryMaterials {
	@Id
	@SequenceGenerator(name = "INVENTORY_MATERIALS_ID_GENERATOR", sequenceName = "INVENTORY_MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVENTORY_MATERIALS_ID_GENERATOR")
	private Long inventoryMaterialsid;
	@ManyToOne
	@JoinColumn(name = "materialsId")
	private Materials materialsId;
	private Long amount;
}
