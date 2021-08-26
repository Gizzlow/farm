package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class HistoryMaterials {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "materialsId")
	private Materials materialsId;
	private String observation;
	private Date date;
	private int input;
	private int output;
	private int residue;
}
