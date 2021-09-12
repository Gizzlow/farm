package com.co.livestockFarm.util;

public enum ConstantMaterials {

	REGISTER_MATERIALS_SUCESSFUL(200, "Material registrado con exito"),
	MATERIALS_REPEAT(400, "El material que ha intentado registrar ya existe"),
	GET_ALL_MATERIALS_SUCESSFUL(200, "Se han obtenido el inventario de materiales"),
	ADD_MATERIALS_SUCESSFUL(200, "Se han agregado los materiales con exito"),
	INVENTORY_MATERIALS_FAIL(200, "No se ha encontrado el inventario"),
	REMOVE_MATERIALS_SUCESSFUL(200, "Se ha removido con exito"),
	REMOVE_MATERIALS_FAIL_MOUNT(400, "Se ha intentado remover mas materiales de los existentes"),
	REMOVE_MATERIALS_FAIL(400, "El producto solicitado no existe en base de datos"),
	DELETE_MATERIALS_SUCESSFUL(200, "El material ha sido eliminado correctamente"),
	DELETE_MATERIALS_FAIL(400, "El producto solicitado no existe en base de datos"),
	ERROR_FATAL(500, "Ha ocurrido un error no controlado");

	private final int statusCode;
	private final String message;

	ConstantMaterials(int statusCode, String message) {
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
