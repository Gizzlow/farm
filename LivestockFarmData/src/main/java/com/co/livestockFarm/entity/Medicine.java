package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Medicine {
	@Id
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
