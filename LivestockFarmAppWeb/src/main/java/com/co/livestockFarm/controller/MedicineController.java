package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.InventoryMedicineDTO;
import com.co.livestockFarm.dto.MedicineDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.MedicineService;
import com.co.livestockFarm.util.ConstantMaterials;
import com.co.livestockFarm.util.ConstantMedicine;

@Controller
@RequestMapping(value = "/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@PostMapping("/registerMedicine")
	@ResponseBody
	public ResponseDTO<Object> registerMedicine(MedicineDTO medicineDTO) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.registerMedicine(medicineDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMedicine.ERROR_FATAL.getStatusCode())
					.message(ConstantMedicine.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/getAllMedicines")
	@ResponseBody
	public ResponseDTO<Object> getAllMedicines() {
		ResponseDTO<Object> response;
		try {
			response = medicineService.getAllMedicines();
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/getAllInventoryMedicines")
	@ResponseBody
	public ResponseDTO<Object> getAllInventoryMedicines() {
		ResponseDTO<Object> response;
		try {
			response = medicineService.getAllInventoryMedicines();
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@PostMapping("/addMedicine")
	@ResponseBody
	public ResponseDTO<Object> addMedicine(InventoryMedicineDTO inventoryMedicineDTO) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.addMedicines(inventoryMedicineDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/getMedicineById/{id}")
	@ResponseBody
	public ResponseDTO<Object> getMedicinesById(@PathVariable("id") Long id) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.getMedicineById(id);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}
	
	@GetMapping("/getInventoryMedicineById/{id}")
	@ResponseBody
	public ResponseDTO<Object> getInventoryMedicineById(@PathVariable("id") Long id) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.getInventoryMedicineById(id);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@PostMapping("/removeMedicines")
	@ResponseBody
	public ResponseDTO<Object> removeMedicines(InventoryMedicineDTO inventoryMedicineDTO) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.removeMedicines(inventoryMedicineDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/deleteMedicine/{id}")
	@ResponseBody
	public ResponseDTO<Object> deleteMedicine(@PathVariable("id") Long id) {
		ResponseDTO<Object> response;
		try {
			response = medicineService.deleteMedicine(id);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

}
