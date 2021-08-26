package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class MedicineDTO {
	private int medicineId;
	private String name;
	private String codeICA;
	private String tantamount;
	private String groupe;
	private String activeIngredient;
	private String measurementUnit;
	private String type;
	private int timeRetirement;
}
