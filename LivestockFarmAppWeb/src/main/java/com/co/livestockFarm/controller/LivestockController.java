package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.livestockFarm.dto.LivestockDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.LivestockService;

@Controller
@RequestMapping(value = "/ganado")
public class LivestockController {

	@Autowired
	LivestockService ganadoService;

	@PostMapping(path = "/agregar")
	public ResponseDTO agregarGanado(@RequestBody LivestockDTO ganado) {

		try {
			ganadoService.ganado();
		} catch (Exception e) {

		}

		return null;
	}

	@GetMapping("/obtenerTodos")
	public String obtenerTodos() {

		try {
			ganadoService.ganado();
		} catch (Exception e) {

		}

		return "Hello world";
	}

	@PostMapping(path = "/editar")
	public ResponseDTO editarGanado(@RequestBody LivestockDTO ganado) {

		try {
			ganadoService.ganado();
		} catch (Exception e) {

		}

		return null;
	}
}
