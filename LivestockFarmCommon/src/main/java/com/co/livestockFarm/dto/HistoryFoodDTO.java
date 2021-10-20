package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class HistoryFoodDTO {
	public HistoryFoodDTO() {
		
	}
	public HistoryFoodDTO(Long food, String dateNow, String icaRegistration,
			String lote, String obsertvation) {
		foodId = food;
		date = dateNow;
		this.icaRegistration = icaRegistration;
		this.lote = lote;
		this.observation = obsertvation;
	}
	private int id;
	public Long foodId;
	private String date;
	private int input;
	private int output;
	private int balance;
	private String icaRegistration;
	private String lote;
	private String observation;
}
