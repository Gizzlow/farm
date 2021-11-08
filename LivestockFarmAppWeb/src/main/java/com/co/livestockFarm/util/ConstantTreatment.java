package com.co.livestockFarm.util;

public enum ConstantTreatment {
	REMOVE_INVENTORY_MEDICINE_AMOUNT_FAIL(400, "La cantidad de medicina que intenta aplicar es mayor a la cantidad existente"),
	REMOVE_INVENTORY_MEDICINE_AMOUNT_SUCCESS(200, "Tratamiento aplicado con éxito");

	private final int statusCode;
	private final String message;

	ConstantTreatment(int statusCode, String message) {
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
