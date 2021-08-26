package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder(toBuilder = true)
public class Food {
	@Id
	private int id;
	private String name;
}
