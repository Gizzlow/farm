package com.co.livestockFarm.entity;

import java.util.Date;

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
public class InventoryMedicine {
	@Id
	@SequenceGenerator(name = "INVENTORY_MEDICINE_ID_GENERATOR", sequenceName = "INVENTORY_MEDICINE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVENTORY_MEDICINE_ID_GENERATOR")
	private Long inventoryMedicineId;
	@ManyToOne
	@JoinColumn(name = "medicineId")
	private Medicine medicineId;
	private Date expirationDate;
	private Long amount;
	private String lot;
}
