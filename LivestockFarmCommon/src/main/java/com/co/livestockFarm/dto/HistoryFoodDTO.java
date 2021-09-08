package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class HistoryFoodDTO {
	public HistoryFoodDTO() {
		
	}
	public HistoryFoodDTO(FoodDTO food, String dateNow, String icaRegistration,
			String lote) {
		foodId = food;
		date = dateNow;
		this.icaRegistration = icaRegistration;
		this.lote = lote;
	}
	private int id;
	public FoodDTO foodId;
	private String date;
	private int input;
	private int output;
	private int balance;
	private String icaRegistration;
	private String lote;
}
