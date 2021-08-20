package com.co.livestockFarm.entity;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Materiales {
	private int id;
	private String nombre;
}
