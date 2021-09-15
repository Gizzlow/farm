package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class MedicineDTO {
	private Long medicineId;
	private String name;
	private String codeICA;
	private String tantamount;
	private String groupe;
	private String activeIngredient;
	private String measurementUnit;
	private String type;
	private String timeRetirement;
}
