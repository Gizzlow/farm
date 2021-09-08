package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class InventoryMaterialsDTO {
	private Long inventoryMaterialsid;
	private Long materialsIdId;
	private MaterialsDTO materialsId;
	private Long amount;
}
