package com.co.livestockFarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.InventoryFoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.Food;
import com.co.livestockFarm.entity.InventoryFood;
import com.co.livestockFarm.repository.FoodRepository;
import com.co.livestockFarm.repository.InventoryFoodRepository;
import com.co.livestockFarm.util.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private InventoryFoodRepository inventoryFoodRepository;

	public ResponseDTO<Object> registerFood(FoodDTO foodDTO) {
		Food initialFood = foodRepository.getMaterialByName(foodDTO.name.toLowerCase());
		if (initialFood == null) {
			Food food = objectMapper.convertValue(foodDTO, Food.class);
			foodRepository.save(food);

			foodDTO.foodId = food.getFoodId();

			return ResponseDTO.builder().statusCode(Constant.FOOD_SUCESSFUL.getStatusCode())
					.message(Constant.FOOD_SUCESSFUL.getMessage()).object(foodDTO).build();
		}

		foodDTO.foodId = initialFood.getFoodId();
		return ResponseDTO.builder().statusCode(Constant.FOOD_REPEATED.getStatusCode())
				.message(Constant.FOOD_REPEATED.getMessage()).object(foodDTO).build();

	}

	public ResponseDTO<Object> addFood(InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> response;

		Food foodFromDB = foodRepository.getFoodById(inventoryFoodDTO.getFoodId().foodId);

		if (foodFromDB != null) {
			InventoryFood inventoryFood = objectMapper.convertValue(inventoryFoodDTO, InventoryFood.class);
			inventoryFoodRepository.save(inventoryFood);
			inventoryFoodDTO.inventoryFoodId = inventoryFood.getInventoryFoodId();
			inventoryFoodDTO.foodId.name = foodFromDB.getName();

			return ResponseDTO.builder().statusCode(Constant.FOOD_SUCESSFUL.getStatusCode())
					.message(Constant.FOOD_SUCESSFUL.getMessage()).object(inventoryFoodDTO).build();
		}

		return ResponseDTO.builder().statusCode(Constant.ENTITY_NOT_FOUND.getStatusCode())
				.message(Constant.ENTITY_NOT_FOUND.getMessage()).object(inventoryFoodDTO).build();

	}

	public ResponseDTO<Object> removeFood(InventoryFoodDTO inventoryFoodDTO) {

		InventoryFood inventoryFoodFromDB = inventoryFoodRepository
				.getInventoryFoodById(inventoryFoodDTO.getInventoryFoodId());

		if (inventoryFoodFromDB != null) {

			if (inventoryFoodDTO.getCantidad() < inventoryFoodFromDB.getCantidad()) {
				inventoryFoodFromDB.setCantidad(inventoryFoodFromDB.getCantidad() - inventoryFoodDTO.getCantidad());
				inventoryFoodRepository.save(inventoryFoodFromDB);
				return ResponseDTO.builder().statusCode(Constant.FOOD_SUBSCTRACT_SUCESSFUL.getStatusCode())
						.message(Constant.FOOD_SUBSCTRACT_SUCESSFUL.getMessage()).build();
			} else {
				return ResponseDTO.builder().statusCode(Constant.FOOD_SUBSCTRACT_FAILED.getStatusCode())
						.message(Constant.FOOD_SUBSCTRACT_FAILED.getMessage()).build();
			}
		}
		return ResponseDTO.builder().statusCode(Constant.ENTITY_NOT_FOUND.getStatusCode())
				.message(Constant.ENTITY_NOT_FOUND.getMessage()).object(inventoryFoodDTO).build();
	}

}
