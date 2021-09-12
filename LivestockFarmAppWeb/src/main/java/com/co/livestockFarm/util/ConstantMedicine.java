package com.co.livestockFarm.util;

public enum ConstantMedicine {
	
	REGISTER_MEDICINE_SUCESSFUL(200, "Medicamento registrado con exito"),
	MEDICINE_REPEAT(400, "El medicamento que ha intentado registrar ya existe"),
	GET_ALL_MEDICINES_SUCESSFUL(200, "Se han obtenido el inventario de medicinas"),
	ERROR_FATAL(500, "Ha ocurrido un error no controlado");
	
	private final int statusCode;
	private final String message;

	ConstantMedicine(int statusCode, String message) {
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
