package com.co.livestockFarm.dto;

import java.util.Date;

public class ReportMaterialsDTO {

	private String name;
	private Date date;
	private String observation;
	private Long input;
	private Long output;
	private Long residue;
	public ReportMaterialsDTO(String name, Date date, String observation, Long input, Long output, Long residue) {
		super();
		this.name = name;
		this.date = date;
		this.observation = observation;
		this.input = input;
		this.output = output;
		this.residue = residue;
	}	
}
