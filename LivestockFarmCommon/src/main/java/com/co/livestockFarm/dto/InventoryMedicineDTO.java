package com.co.livestockFarm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InventoryMedicineDTO {
	private Long inventoryMedicineId;
	private Long medicineId;
	private Date expirationDate;
	private Long amount;
	private String lot;
	
	//extras
	
	private String name;
	private String activeIngredient;
	private String measurementUnit;
}
