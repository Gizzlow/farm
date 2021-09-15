package com.co.livestockFarm.entity;

import java.util.Date;

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
public class HistoryMaterials {
	@Id
	@SequenceGenerator(name = "HISTORY_MATERIALS_ID_GENERATOR", sequenceName = "HISTORY_MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORY_MATERIALS_ID_GENERATOR")
	private int id;
	@ManyToOne
	@JoinColumn(name = "materialsId")
	private Materials materialsId;
	private String observation;
	private Date date;
	private Long input;
	private Long output;
	private Long residue;
}
