package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class ReportTreatmentDTO {
	private String date;
	private String name;
	private String type;
	private String treatment;
	private String medicineName;
	private String expirationDate;
	private String medicineLot;
	private String icaCode;
	private int amount;
	private String medicineType;
	private String retireTime;
	private String endTreatment;
	private String personEncharge;

	public ReportTreatmentDTO(String date, String name, String type, String treatment, String medicineName,
			String expirationDate, String medicineLot, String icaCode, int amount, String medicineType,
			String retireTime, String endTreatment, String personEncharge) {
		super();
		this.date = date;
		this.name = name;
		this.type = type;
		this.treatment = treatment;
		this.medicineName = medicineName;
		this.expirationDate = expirationDate;
		this.medicineLot = medicineLot;
		this.icaCode = icaCode;
		this.amount = amount;
		this.medicineType = medicineType;
		this.retireTime = retireTime;
		this.endTreatment = endTreatment;
		this.personEncharge = personEncharge;
	}
}
