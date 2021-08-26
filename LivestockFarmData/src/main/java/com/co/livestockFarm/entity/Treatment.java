package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Treatment {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "livestockId")
	private Livestock livestockId;
	@ManyToOne
	@JoinColumn(name = "medicineId")
	private Medicine medicineId;
	private Date date;
	private Date expirationDate;
	private String medicineLot;
	private int amount;
	private String treatment;
	private Date endTreatment;
	private String personEncharge;
}
