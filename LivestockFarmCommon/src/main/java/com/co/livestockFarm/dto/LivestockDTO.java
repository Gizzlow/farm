package com.co.livestockFarm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class LivestockDTO {
	private Long livestockId;
	private String name;
	private Long motherId;
	private String type;
	private boolean active;
	private String observation;
}
