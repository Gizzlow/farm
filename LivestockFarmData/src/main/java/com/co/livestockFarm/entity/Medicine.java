package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Medicine {
	@Id
	@SequenceGenerator(name = "MEDICINE_ID_GENERATOR", sequenceName = "MEDICINE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICINE_ID_GENERATOR")
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
