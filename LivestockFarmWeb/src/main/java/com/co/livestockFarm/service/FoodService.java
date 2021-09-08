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
		ResponseDTO<Object> response = new ResponseDTO<>();
		Food initialFood = foodRepository.getMaterialByName(foodDTO.name.toLowerCase());
		if (initialFood == null) {
			Food food = objectMapper.convertValue(foodDTO, Food.class);
			foodRepository.save(food);
			response.setStatusCode(Constant.FOOD_SUCESSFUL.getStatusCode());
			response.setMessage(Constant.FOOD_SUCESSFUL.getMessage());
			foodDTO.foodId = food.getFoodId();
			response.setObject(foodDTO);
		} else {
			response.setStatusCode(Constant.FOOD_REPEATED.getStatusCode());
			response.setMessage(Constant.FOOD_REPEATED.getMessage());
			foodDTO.foodId = initialFood.getFoodId();
			response.setObject(foodDTO);
		}
		return response;
	}

	public ResponseDTO<Object> addFood(InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> response = new ResponseDTO<>();

		Food foodFromDB = foodRepository.getFoodById(inventoryFoodDTO.getFoodId().foodId);

		if (foodFromDB != null) {
			InventoryFood inventoryFood = objectMapper.convertValue(inventoryFoodDTO, InventoryFood.class);
			inventoryFoodRepository.save(inventoryFood);
			response.setStatusCode(Constant.FOOD_SUCESSFUL.getStatusCode());
			response.setMessage(Constant.FOOD_SUCESSFUL.getMessage());
			inventoryFoodDTO.inventoryFoodId = inventoryFood.getInventoryFoodId();
			inventoryFoodDTO.foodId.name = foodFromDB.getName();
			response.setObject(inventoryFoodDTO);
		} else {
			response.setStatusCode(Constant.ENTITY_NOT_FOUND.getStatusCode());
			response.setMessage(Constant.ENTITY_NOT_FOUND.getMessage());
			response.setObject(inventoryFoodDTO);
		}

		return response;
	}

	public ResponseDTO<Object> removeFood(InventoryFoodDTO inventoryFoodDTO) {
		ResponseDTO<Object> response = new ResponseDTO<>();

		InventoryFood inventoryFoodFromDB = inventoryFoodRepository.getInventoryFoodById(inventoryFoodDTO.getInventoryFoodId());

		if (inventoryFoodFromDB != null) {

			if (inventoryFoodDTO.getCantidad() < inventoryFoodFromDB.getCantidad()) {
				inventoryFoodFromDB.setCantidad(inventoryFoodFromDB.getCantidad() - inventoryFoodDTO.getCantidad());
				inventoryFoodRepository.save(inventoryFoodFromDB);
				response.setStatusCode(Constant.FOOD_SUBSCTRACT_SUCESSFUL.getStatusCode());
				response.setMessage(Constant.FOOD_SUBSCTRACT_SUCESSFUL.getMessage());
			} else {
				response.setStatusCode(Constant.FOOD_SUBSCTRACT_FAILED.getStatusCode());
				response.setMessage(Constant.FOOD_SUBSCTRACT_FAILED.getMessage());
			}
		} else {
			response.setStatusCode(Constant.ENTITY_NOT_FOUND.getStatusCode());
			response.setMessage(Constant.ENTITY_NOT_FOUND.getMessage());

		}
		response.setObject(inventoryFoodDTO);
		return response;
	}

}
