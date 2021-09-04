package com.co.livestockFarm.dto;

import lombok.Data;

@Data
public class ResponseDTO <T>{
	public int statusCode;
	public String message;
	public T object;
}
