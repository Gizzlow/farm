package com.co.livestockFarm.util;

public enum ConstantFood {


	FOOD_SUCESSFUL(200, "Alimento registrado con éxito"),
	FOOD_SUBSCTRACT_SUCESSFUL(200, "Alimento retirado con éxito"),
	FOOD_SUBSCTRACT_FAILED(400, "Hay menos unidades de la que intenta retirar"),
	FOOD_REPEATED(400, "El alimento que ha intentado registrar ya existe"),
	ENTITY_NOT_FOUND(404, "Entidad no encontrada"),
	INPUT_OPERATION_TYPE(1, "Añadido"),
	OUTPUT_OPERATION_TYPE(1, "Retirado"),
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
