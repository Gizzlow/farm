package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.service.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@PostMapping("/registerFood")
	public ResponseDTO registerFood(@RequestBody FoodDTO foodDTO) {
		ResponseDTO<FoodDTO> responseDTO = new ResponseDTO<>();

		try {
			FoodDTO foodFromDB = foodService.registerFood(foodDTO.foodId);

			responseDTO.message = foodFromDB.name;
			responseDTO.statusCode = 200;
		} catch (Exception e) {
			responseDTO.message = "EX";
			responseDTO.statusCode = 500;
		}
		return responseDTO;
	}

	@PostMapping("/addFood")
	public String addFood(@RequestBody FoodDTO alimentos) {

		try {
//			foodService.agregarAlimentos();
		} catch (Exception e) {

		}

		return "Hello World";
	}

}
