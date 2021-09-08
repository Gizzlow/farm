package com.co.livestockFarm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO <T>{
	public int statusCode;
	public String message;
	public T object;
}
