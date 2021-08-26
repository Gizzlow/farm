package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class InventoryMedicine {
	@Id
	private int inventoryMedicineId;
}
