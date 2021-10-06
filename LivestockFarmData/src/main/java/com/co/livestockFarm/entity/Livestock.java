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
public class Livestock {
	@Id
	@SequenceGenerator(name = "LIVESTOCK_ID_GENERATOR", sequenceName = "LIVESTOCK_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIVESTOCK_ID_GENERATOR")
	private long livestockId;
	private String name;
	@ManyToOne
	@JoinColumn(name = "motherId")
	private Livestock motherId;
	private String type;
	private boolean active;
	private String observation;
	
	public Livestock() {
		
	}
}
