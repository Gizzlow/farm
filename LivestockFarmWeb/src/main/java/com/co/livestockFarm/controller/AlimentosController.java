package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.livestockFarm.dto.AlimentosDTO;
import com.co.livestockFarm.service.AlimentosService;

@Controller
@RequestMapping(value = "/alimentos")
public class AlimentosController {

	@Autowired
	private AlimentosService alimentosService;

	@PostMapping("/registrarAlimentos")
	public String registrarAlimentos(@RequestBody AlimentosDTO alimentos) {

		try {
			alimentosService.registrarAlimentos();
		} catch (Exception e) {

		}

		return "Hello World";
	}

	@PostMapping("/agregarAlimentos")
	public String agregarAlimentos(@RequestBody AlimentosDTO alimentos) {

		try {
			alimentosService.agregarAlimentos();
		} catch (Exception e) {

		}

		return "Hello World";
	}

}
