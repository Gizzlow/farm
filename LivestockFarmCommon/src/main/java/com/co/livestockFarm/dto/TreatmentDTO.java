package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class TreatmentDTO {
	private Long id;
	private Long livestockId;
	private Long medicineId;
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
	private Long inventoryId;
}
