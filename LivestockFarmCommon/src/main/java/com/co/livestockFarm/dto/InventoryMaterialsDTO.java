package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class InventoryMaterialsDTO {
	private Long inventoryMaterialsId;
	private Long materialsId;
	private Long amount;
	private String name;
	private String observation;
}
