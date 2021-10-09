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
public class InventoryFood {
	@Id
	@SequenceGenerator(name = "MATERIALS_ID_GENERATOR", sequenceName = "MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIALS_ID_GENERATOR")
	private long inventoryFoodId;
	@ManyToOne
	@JoinColumn(name = "foodId")
	private Food foodId;
	private int cantidad;
	private String lote;
	private String registroIca;
	private String fechaVencimiento;
	private String nombreAlmacen;
	private String observation;
	
	public InventoryFood() {
		
	}
}
