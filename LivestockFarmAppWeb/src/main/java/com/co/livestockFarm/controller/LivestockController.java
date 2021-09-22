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
import com.co.livestockFarm.util.ConstantFood;

@Controller
@RequestMapping(value = "/ganado")
public class LivestockController {

	@Autowired
	LivestockService ganadoService;

	@PostMapping(path = "/agregar")
	public ResponseDTO<Object> agregarGanado(@RequestBody LivestockDTO ganadoDTO) {
		ResponseDTO<Object> responseDTO;
		try {
			responseDTO = ganadoService.registerLivestock(ganadoDTO);
		} catch (Exception e) {
			return ResponseDTO.builder()
					.statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage())
					.object(ganadoDTO)
					.build();
		}

		return responseDTO;
	}

	@GetMapping("/obtenerTodos")
	public String obtenerTodos() {

		try {
			
		} catch (Exception e) {

		}

		return "Hello world";
	}

	@PostMapping(path = "/editar")
	public ResponseDTO<Object> editarGanado(@RequestBody LivestockDTO ganado) {

		try {
			
		} catch (Exception e) {

		}

		return null;
	}
}
