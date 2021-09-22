package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.InventoryMaterialsDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.MaterialsService;
import com.co.livestockFarm.util.ConstantMaterials;

@Controller
@RequestMapping(value = "/materials")
public class MaterialsController {

	@Autowired
	private MaterialsService materialsService;

	@PostMapping("/registerMaterials")
	@ResponseBody
	public ResponseDTO<Object> registerMaterials(MaterialsDTO materialsDTO) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.registerMaterials(materialsDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/getAllMaterials")
	@ResponseBody
	public ResponseDTO<Object> getAllMaterials() {
		ResponseDTO<Object> response;
		try {
			response = materialsService.getAllMaterials();
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@PostMapping("/addMaterials")
	@ResponseBody
	public ResponseDTO<Object> addMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.addMaterials(inventoryMaterialsDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@PostMapping("/removeMaterials")
	@ResponseBody
	public ResponseDTO<Object> removeMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.removeMaterials(inventoryMaterialsDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@GetMapping("/deleteMaterials/{id}")
	@ResponseBody
	public ResponseDTO<Object> deleteMaterials(@PathVariable("id") Long id) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.deleteMaterials(id);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantMaterials.ERROR_FATAL.getStatusCode())
					.message(ConstantMaterials.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}
}
