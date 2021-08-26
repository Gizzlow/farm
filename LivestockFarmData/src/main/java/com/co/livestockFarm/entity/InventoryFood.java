package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder(toBuilder = true)
public class InventoryFood {
	@Id
	private long inventoryFoodId;
	@ManyToOne
	@JoinColumn(name = "foodId")
	private Food foodId;
}
