package com.co.livestockFarm.util;

public enum Constant {

	MATERIALS_SUCESSFUL(200, "Material registrado con exito"),
	MATERIALS_REPEAT(400, "El material que ha intentado registrar ya existe"),
	ERROR_FATAL(500, "Ha ocurrido un error no controlado"),
	FOOD_SUCESSFUL(200, "Alimento registrado con éxito"),
	FOOD_SUBSCTRACT_SUCESSFUL(200, "Alimento retirado con éxito"),
	FOOD_SUBSCTRACT_FAILED(400, "Hay menos unidades de la que intenta retirar"),
	FOOD_REPEATED(400, "El alimento que ha intentado registrar ya existe"),
	ENTITY_NOT_FOUND(404, "Entidad no encontrada");

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
