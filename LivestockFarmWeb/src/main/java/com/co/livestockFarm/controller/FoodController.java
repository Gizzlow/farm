package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.service.FoodService;

@Controller
@RequestMapping(value = "/alimentos")
public class FoodController {

	@Autowired
	private FoodService alimentosService;

	@PostMapping("/registrarAlimentos")
	public String registrarAlimentos(@RequestBody FoodDTO alimentos) {

		try {
			alimentosService.registrarAlimentos();
		} catch (Exception e) {

		}

		return "Hello World";
	}

	@PostMapping("/agregarAlimentos")
	public String agregarAlimentos(@RequestBody FoodDTO alimentos) {

		try {
			alimentosService.agregarAlimentos();
		} catch (Exception e) {

		}

		return "Hello World";
	}

}
