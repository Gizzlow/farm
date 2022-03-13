package com.co.livestockFarm.util;

public enum ConstantReport {
	ERROR_FATAL(500, "Error en las fechas para la consulta, por favor revise"),
	ERROR_BUILDING_REPORT(500, "Error en la construcción del reporte"),
	SUCCESS_REPORT(200, "El reporte se ha generado con éxito");

	private final int statusCode;
	private final String message;

	ConstantReport(int statusCode, String message) {
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
