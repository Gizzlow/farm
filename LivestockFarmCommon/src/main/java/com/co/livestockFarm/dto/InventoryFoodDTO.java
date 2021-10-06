package com.co.livestockFarm.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class InventoryFoodDTO {
	public Long inventoryFoodId;
//	private int foodId;
	public Long foodId;
	private int cantidad;
	private String lote;
	private String registroIca;
	private String fechaVencimiento;
	private String nombreAlmacen;
	private String operationDate;
	private String name;
}
