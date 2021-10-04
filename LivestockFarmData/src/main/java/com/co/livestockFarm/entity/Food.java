package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Food {
	@Id
	@SequenceGenerator(name = "FOOD_ID_GENERATOR", sequenceName = "FOOD_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOOD_ID_GENERATOR")
	private Long foodId;
	private String name;
	public Food() {
		
	}
}
