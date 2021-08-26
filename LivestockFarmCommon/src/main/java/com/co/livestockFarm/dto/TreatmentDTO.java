package com.co.livestockFarm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TreatmentDTO {
	private int id;
	private int livestockId;
	private int medicineId;
	private Date date;
	private Date expirationDate;
	private String medicineLot;
	private int amount;
	private String treatment;
	private Date endTreatment;
	private String personEncharge;
}
