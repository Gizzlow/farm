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
public class HistoryMedicine {
	@Id
	@SequenceGenerator(name = "HISTORY_MEDICINE_ID_GENERATOR", sequenceName = "HISTORY_MEDICINE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORY_MEDICINE_ID_GENERATOR")
	private int historyMedicineid;
	@ManyToOne
	@JoinColumn(name = "medicineId")
	private Medicine medicineId;
	private String observation;
}
