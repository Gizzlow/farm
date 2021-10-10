package com.co.livestockFarm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Materials {
	@Id
	@SequenceGenerator(name = "MATERIALS_ID_GENERATOR", sequenceName = "MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIALS_ID_GENERATOR")
	private Long materialsId;
	@Column(unique = true)
	private String name;
}
