package com.co.livestockFarm.controller;

import org.springframework.stereotype.Controller;
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
	}

}
