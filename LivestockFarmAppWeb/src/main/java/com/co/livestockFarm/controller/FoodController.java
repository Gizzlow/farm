package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.InventoryFoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.service.FoodService;
import com.co.livestockFarm.util.ConstantFood;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@PostMapping("/registerFood")
	public ResponseDTO<Object> registerFood(@RequestBody FoodDTO foodDTO) {
		ResponseDTO<Object> responseDTO;

		try {
			responseDTO = foodService.registerFood(foodDTO);
		} catch (Exception e) {			
			return ResponseDTO.builder()
			.statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
			.message(ConstantFood.ERROR_FATAL.getMessage())
			.object(foodDTO)
			.build();
		}
		return responseDTO;
	}

	@PostMapping("/addFood")
	public ResponseDTO<Object> addFood(@RequestBody InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> responseDTO;
		
		try {
			responseDTO = foodService.addFood(inventoryFoodDTO);
		} catch (Exception e) {
			return ResponseDTO.builder()
					.statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(e.getMessage())
					.object(inventoryFoodDTO)
					.build();
		}

		return responseDTO;
	}
	
	@PostMapping("/removeFood")
	public ResponseDTO<Object> removeFood(@RequestBody InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> responseDTO;
		
		try {
			responseDTO = foodService.removeFood(inventoryFoodDTO);
		} catch (Exception e) {
			return ResponseDTO.builder()
					.statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(e.getMessage())
					.object(inventoryFoodDTO)
					.build();
		}

		return responseDTO;
	}
	@GetMapping("/getAllFood")
	public ResponseDTO<Object> getAllFood(){
		
		ResponseDTO<Object> response;
		try {
			response = foodService.getAllFood();
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).build();
		}

		return response;
	}

}
