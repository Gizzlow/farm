package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class InventoryFoodDTO {
	public long inventoryFoodId;
//	private int foodId;
	public FoodDTO foodId;
	private int cantidad;
	private String lote;
	private String registroIca;
	private String fechaVencimiento;
	private String nombreAlmacen;
	private String operationDate;
}
