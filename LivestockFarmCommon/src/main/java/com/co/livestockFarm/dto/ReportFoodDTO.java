package com.co.livestockFarm.dto;

import java.util.Date;

import lombok.Data;
@Data
public class ReportFoodDTO {

	private String name;
	private String date;
	private Integer input;
	private Integer output;
	private Integer balance;
	private Date expirationDate;
	private String nombreAlmacen;
	private String icaRegistration;
	private String lote;
	private String observation;
	public ReportFoodDTO(String name, String date, Integer input, Integer output, Integer balance, Date expirationDate,
			String nombreAlmacen, String icaRegistration, String lote, String observation) {
		super();
		this.name = name;
		this.date = date;
		this.input = input;
		this.output = output;
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.nombreAlmacen = nombreAlmacen;
		this.icaRegistration = icaRegistration;
		this.lote = lote;
		this.observation = observation;
	}
	
	
}
