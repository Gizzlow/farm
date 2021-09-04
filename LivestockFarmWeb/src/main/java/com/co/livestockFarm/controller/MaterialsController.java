package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.service.MaterialsService;

@RestController
@RequestMapping(value = "/materials")
public class MaterialsController {

	@Autowired
	private MaterialsService materialsService;

	@PostMapping("/registerMaterials")
	public ResponseEntity<?> registerMaterials(@RequestBody MaterialsDTO materials) {
		try {
			materialsService.getMaterialsByName(materials.getName());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PostMapping("/addMaterials")
	public String addMaterials(@RequestBody FoodDTO alimentos) {

		try {

		} catch (Exception e) {

		}

		return "Hello World";
	}
}
