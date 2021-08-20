package com.co.livestockFarm.entity;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder(toBuilder = true)
public class Alimentos {
	private int id;
	private String nombre;
}
