package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.FoodDTO;
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
	public ResponseDTO registerMaterials(@RequestBody MaterialsDTO materials) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = materialsService.registerMaterials(materials);
		} catch (Exception e) {

			response.setStatusCode(Constant.ERROR_FATAL.getStatusCode());
			response.setMessage(Constant.ERROR_FATAL.getMessage());
			return response;
		}

		return response;
	}

	@PostMapping("/addMaterials")
	public String addMaterials(@RequestBody FoodDTO alimentos) {

		try {

		} catch (Exception e) {

		}

		return "Hello World";
	}
}
