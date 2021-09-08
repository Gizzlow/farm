package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.InventoryFoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.FoodService;
import com.co.livestockFarm.util.Constant;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@PostMapping("/registerFood")
	public ResponseDTO<Object> registerFood(@RequestBody FoodDTO foodDTO) {
		ResponseDTO<Object> responseDTO = new ResponseDTO<>();

		try {
			responseDTO = foodService.registerFood(foodDTO);
		} catch (Exception e) {
			responseDTO.message = e.getMessage();
			responseDTO.statusCode = Constant.ERROR_FATAL.getStatusCode();
			responseDTO.setObject(foodDTO);
		}
		return responseDTO;
	}

	@PostMapping("/addFood")
	public ResponseDTO<Object> addFood(@RequestBody InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> responseDTO = new ResponseDTO<>();
		
		try {
			responseDTO = foodService.addFood(inventoryFoodDTO);
		} catch (Exception e) {
			responseDTO.message = e.getMessage();
			responseDTO.statusCode = Constant.ERROR_FATAL.getStatusCode();
			responseDTO.setObject(inventoryFoodDTO);
		}

		return responseDTO;
	}
	
	@PostMapping("/removeFood")
	public ResponseDTO<Object> removeFood(@RequestBody InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> responseDTO = new ResponseDTO<>();
		
		try {
			responseDTO = foodService.removeFood(inventoryFoodDTO);
		} catch (Exception e) {
			responseDTO.message = e.getMessage();
			responseDTO.statusCode = Constant.ERROR_FATAL.getStatusCode();
			responseDTO.setObject(inventoryFoodDTO);
		}

		return responseDTO;
	}

}
