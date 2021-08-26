package com.co.livestockFarm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HistoryMaterialsDTO {
	private int id;
	private int materialsId;
	private String observation;
	private Date date;
	private int input;
	private int output;
	private int residue;
}
