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
public class Treatment {
	@Id
	@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "livestockId")
	private Livestock livestockId;
//	@ManyToOne
//	@JoinColumn(name = "medicineId")
//	private Medicine medicineId;
	private String date;
	private String expirationDate;
	private String medicineLot;
	private int amount;
	private String treatment;
	private String endTreatment;
	private String personEncharge;
	private String medicineName;
	private String icaCode;
	private String medicineType;
	private String retireTime;
}
