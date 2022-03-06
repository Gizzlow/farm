package com.co.livestockFarm.dto;

import java.util.Date;

public class ReportMedicineDTO {

	private String name;
	private String activeIngredient;
	private String codeICA;
	private Date date;
	private Long input;
	private Long output;
	private Long residue;
	private Date expirationDate;
	private String lot;
	
	public ReportMedicineDTO(String name, String activeIngredient, String codeICA, Date date, Long input, Long output,
			Long residue, Date expirationDate, String lot) {
		super();
		this.name = name;
		this.activeIngredient = activeIngredient;
		this.codeICA = codeICA;
		this.date = date;
		this.input = input;
		this.output = output;
		this.residue = residue;
		this.expirationDate = expirationDate;
		this.lot = lot;
	}
}
