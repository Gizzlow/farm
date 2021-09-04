package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder(toBuilder = true)
public class Food {
	@Id
	@SequenceGenerator(name = "MATERIALS_ID_GENERATOR", sequenceName = "MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIALS_ID_GENERATOR")
	private int foodId;
	private String name;
}
