package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.LivestockDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.LivestockService;
import com.co.livestockFarm.util.ConstantFood;

@Controller
@RequestMapping(value = "/livestock")
public class LivestockController {

	@Autowired
	LivestockService ganadoService;

	@PostMapping(path = "/agregar")
	@ResponseBody
	public ResponseDTO<Object> agregarGanado(LivestockDTO ganadoDTO) {
		ResponseDTO<Object> responseDTO;
		try {
			responseDTO = ganadoService.registerLivestock(ganadoDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).object(ganadoDTO).build();
		}

		return responseDTO;
	}

	@GetMapping("/obtenerTodos")
	@ResponseBody
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

	@PostMapping("/editar")
	@ResponseBody
	public ResponseDTO<Object> editarGanado(LivestockDTO ganado) {

		ResponseDTO<Object> response;
		try {
			response = ganadoService.editLiveStock(ganado);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}
}
