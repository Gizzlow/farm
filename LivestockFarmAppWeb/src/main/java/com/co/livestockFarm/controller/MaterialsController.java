package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.InventoryMaterialsDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.MaterialsService;
import com.co.livestockFarm.util.Constant;

@RestController
@RequestMapping(value = "/materials")
public class MaterialsController {

	@Autowired
	private MaterialsService materialsService;

	@PostMapping("/registerMaterials")
	public ResponseDTO<Object> registerMaterials(@RequestBody MaterialsDTO materials) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.registerMaterials(materials);
		} catch (Exception e) {

			return ResponseDTO.builder()
					.statusCode(Constant.ERROR_FATAL.getStatusCode())
					.message(Constant.ERROR_FATAL.getMessage())
					.build();
		}

		return response;
	}

	@PostMapping("/addMaterials")
	public ResponseDTO<Object> addMaterials(@RequestBody InventoryMaterialsDTO inventoryMaterialsDTO) {
		ResponseDTO<Object> response;
		try {
			response = materialsService.addMaterials(inventoryMaterialsDTO);
		} catch (Exception e) {

			return ResponseDTO.builder()
					.statusCode(Constant.ERROR_FATAL.getStatusCode())
					.message(Constant.ERROR_FATAL.getMessage())
					.build();
		}

		return response;
	}
	
	@PostMapping("/removeMaterials")
	public ResponseDTO<Object> removeMaterials(@RequestBody InventoryMaterialsDTO inventoryMaterialsDTO){
		ResponseDTO<Object> response;
		try {
			response = materialsService.removeMaterials(inventoryMaterialsDTO);
		} catch (Exception e) {

			return ResponseDTO.builder()
					.statusCode(Constant.ERROR_FATAL.getStatusCode())
					.message(Constant.ERROR_FATAL.getMessage())
					.build();
		}

		return response;
	}
}
