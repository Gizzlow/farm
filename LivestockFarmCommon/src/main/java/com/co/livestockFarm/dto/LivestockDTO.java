package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class LivestockDTO {
	private long livestockId;
	private String name;
	private LivestockDTO motherId;
	private String type;
	private boolean active;
	private String observation;
}
