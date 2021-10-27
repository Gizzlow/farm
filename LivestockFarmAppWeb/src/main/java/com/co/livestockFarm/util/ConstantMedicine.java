package com.co.livestockFarm.util;

public enum ConstantMedicine {
	
	REGISTER_MEDICINE_SUCESSFUL(200, "Medicamento registrado con exito"),
	MEDICINE_REPEAT(400, "El medicamento que ha intentado registrar ya existe"),
	GET_ALL_MEDICINES_SUCESSFUL(200, "Se han obtenido el inventario de medicinas"),
	MEDICINE_ADD(200, "Se ha agregado medicina con exito"),
	MEDICINE_ADD_ERROR(400, "Se ha intentado agregar una medicina inexistente"),
	GET_INVENTORY_BY_ID(200, "Se ha obtenido satisfactoriamente"),
	GET_INVENTORY_BY_ID_ERROR(400, "No existe"),
	REMOVE_MEDICINE_SUCESSFUL(200, "Se ha removido correctamente"),
	REMOVE_MEDICINE_AMOUNT_FAIL(400, "La cantidad que intenta remover es mayor a la cantidad existente"),
	REMOVE_MEDICINE_ERROR(400, "Se ha removido correctamente"),
	DELETE_MEDICINE_SUCESSFUL(200, "Medicina eliminada correctamente"),
	DELETE_MEDICINE_ERROR(400, "ha ocurrido un error al intentar eliminar la medicina"),
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
