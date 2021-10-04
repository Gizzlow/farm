package com.co.livestockFarm.util;

public enum ConstantLivestock {
	LIVESTOCK_SUCCESSFUL(200, "Ganado registrado con éxito"),
	LIVESTOCK_REPEATED(400, "El ganado que ha intentado registrar ya existe"),
	ENTITY_NOT_FOUND(404, "Entidad no encontrada"),
	ERROR_FATAL(500, "Ha ocurrido un error no controlado"),
	GET_ALL_LIVESTOCK_SUCESSFUL(200, "Ganado listado correctamente");
	
	private final int statusCode;
	private final String message;
	
	ConstantLivestock(int statusCode, String message) {
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
