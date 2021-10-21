package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.LivestockDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.LivestockService;
import com.co.livestockFarm.util.ConstantFood;

@RestController
@RequestMapping(value = "/livestock")
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
	public ResponseDTO<Object> obtenerTodos() {

		ResponseDTO<Object> response;
		try {
			response = ganadoService.getAllLivestock();
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

	@PostMapping(path = "/editar")
	public ResponseDTO<Object> editarGanado(@RequestBody LivestockDTO ganado) {

		try {
			
		} catch (Exception e) {

		}

		return null;
	}
}
