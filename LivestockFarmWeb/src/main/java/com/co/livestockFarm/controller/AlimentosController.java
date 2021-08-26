package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;

@RestController
public class AlimentosController {
	
	@PostMapping("api/alimentos")
	public ResponseDTO alimentos(@RequestBody FoodDTO alimento) {
		ResponseDTO res = new ResponseDTO();
		res.statusCode = 200;
		res.message = "ssss";
		res.object = alimento;
		return res;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.service.AlimentosService;

@Controller
@RequestMapping(value = "/alimentos")
public class AlimentosController {

	@Autowired
	private AlimentosService alimentosService;

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
>>>>>>> ad3f0b31d1a3a14c6787a17e6f7438efcb3fddde
	}

}
