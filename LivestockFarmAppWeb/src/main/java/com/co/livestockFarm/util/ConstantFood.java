package com.co.livestockFarm.util;

public enum ConstantFood {

	ERROR_FATAL(500, "Ha ocurrido un error no controlado");

	private final int statusCode;
	private final String message;

	ConstantFood(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {

		return statusCode;
	}

	public String getMessage() {

		return message;
	}

}
