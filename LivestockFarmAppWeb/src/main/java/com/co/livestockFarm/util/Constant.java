package com.co.livestockFarm.util;

public enum Constant {

	MATERIALS_SUCESSFUL(200, "Material registrado con exito"),
	MATERIALS_REPEAT(400, "El material que ha intentado registrar ya existe"),
	INVENTORY_MATERIALS_SUCESSFUL(200,"El material se ha agregado con exito"),
	ERROR_FATAL(500, "Ha ocurrido un error no controlado");

	private final int statusCode;
	private final String message;

	Constant(int statusCode, String message) {
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
