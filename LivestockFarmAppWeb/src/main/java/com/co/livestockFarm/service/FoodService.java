package com.co.livestockFarm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.dto.HistoryFoodDTO;
import com.co.livestockFarm.dto.InventoryFoodDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.Food;
import com.co.livestockFarm.entity.HistoryFood;
import com.co.livestockFarm.entity.InventoryFood;
import com.co.livestockFarm.repository.FoodRepository;
import com.co.livestockFarm.repository.HistoryFoodRepository;
import com.co.livestockFarm.repository.InventoryFoodRepository;
import com.co.livestockFarm.util.ConstantFood;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private InventoryFoodRepository inventoryFoodRepository;

	@Autowired
	private HistoryFoodRepository historyFoodRepository;

	public ResponseDTO<Object> registerFood(FoodDTO foodDTO) {
		Food initialFood = foodRepository.getMaterialByName(foodDTO.name.toLowerCase());
		if (initialFood == null) {
			Food food = objectMapper.convertValue(foodDTO, Food.class);
			food.setName(food.getName().toLowerCase());
			foodRepository.save(food);
			foodDTO.foodId = food.getFoodId();

			return ResponseDTO.builder().statusCode(ConstantFood.FOOD_SUCESSFUL.getStatusCode())
					.message(ConstantFood.FOOD_SUCESSFUL.getMessage()).object(foodDTO).build();
		}
		foodDTO.foodId = initialFood.getFoodId();

		return ResponseDTO.builder().statusCode(ConstantFood.FOOD_REPEATED.getStatusCode())
				.message(ConstantFood.FOOD_REPEATED.getMessage()).object(foodDTO).build();

	}

	public ResponseDTO<Object> addFood(InventoryFoodDTO inventoryFoodDTO) {

		Food foodFromDB = foodRepository.getFoodById(inventoryFoodDTO.getFoodId());
		String operation = "default";
		int initialAmount = 0;

		if (foodFromDB != null) {
			InventoryFood inventoryFood;
			if (inventoryFoodDTO.getInventoryFoodId() == null) {
				inventoryFood = new InventoryFood();
				inventoryFood.setFoodId(foodFromDB);
				inventoryFood.setCantidad(inventoryFoodDTO.getCantidad());
				inventoryFood.setFechaVencimiento(inventoryFoodDTO.getFechaVencimiento());
				inventoryFood.setLote(inventoryFoodDTO.getLote());
				inventoryFood.setNombreAlmacen(inventoryFoodDTO.getNombreAlmacen());
				inventoryFood.setRegistroIca(inventoryFoodDTO.getRegistroIca());

			} else {
				inventoryFood = inventoryFoodRepository.getInventoryFoodById(inventoryFoodDTO.getInventoryFoodId());
				initialAmount = inventoryFood.getCantidad();
				inventoryFood.setCantidad(initialAmount + inventoryFoodDTO.getCantidad());
				operation = ConstantFood.INPUT_OPERATION_TYPE.getMessage();
			}
			inventoryFood.setObservation(inventoryFoodDTO.getObservation());

			inventoryFoodRepository.save(inventoryFood);
			inventoryFoodDTO.inventoryFoodId = inventoryFood.getInventoryFoodId();
			inventoryFoodDTO.setName(foodFromDB.getName());

			FoodDTO food = objectMapper.convertValue(foodFromDB, FoodDTO.class);
			String dateNow = LocalDateTime.now().toLocalDate().toString();

			HistoryFoodDTO traceAdd = new HistoryFoodDTO(food.getFoodId(), dateNow, inventoryFoodDTO.getRegistroIca(),
					inventoryFoodDTO.getLote(), inventoryFood.getObservation());
			registerTrace(traceAdd, inventoryFoodDTO.getCantidad(), operation, initialAmount);

			return ResponseDTO.builder().statusCode(ConstantFood.FOOD_SUCESSFUL.getStatusCode())
					.message(ConstantFood.FOOD_SUCESSFUL.getMessage()).object(inventoryFoodDTO).build();
		}

		return ResponseDTO.builder().statusCode(ConstantFood.ENTITY_NOT_FOUND.getStatusCode())
				.message(ConstantFood.ENTITY_NOT_FOUND.getMessage()).object(inventoryFoodDTO).build();

	}

	public ResponseDTO<Object> removeFood(InventoryFoodDTO inventoryFoodDTO) {

		InventoryFood inventoryFoodFromDB = inventoryFoodRepository
				.getInventoryFoodById(inventoryFoodDTO.getInventoryFoodId());

		if (inventoryFoodFromDB != null) {

			if (inventoryFoodDTO.getCantidad() <= inventoryFoodFromDB.getCantidad()) {
				int initialAmount = inventoryFoodFromDB.getCantidad();
				inventoryFoodFromDB.setCantidad(inventoryFoodFromDB.getCantidad() - inventoryFoodDTO.getCantidad());
				inventoryFoodFromDB.setObservation(inventoryFoodDTO.getObservation());
				inventoryFoodRepository.save(inventoryFoodFromDB);

				Food food = foodRepository.getFoodById(inventoryFoodFromDB.getFoodId().getFoodId());
				FoodDTO foodDTO = objectMapper.convertValue(food, FoodDTO.class);

				String dateNow = LocalDateTime.now().toLocalDate().toString();

				int amount = inventoryFoodDTO.getCantidad();

				HistoryFoodDTO traceAdd = new HistoryFoodDTO(foodDTO.getFoodId(), dateNow,
						inventoryFoodFromDB.getRegistroIca(), inventoryFoodFromDB.getLote(),
						inventoryFoodFromDB.getObservation());

				registerTrace(traceAdd, amount, ConstantFood.OUTPUT_OPERATION_TYPE.getMessage(), initialAmount);

				return ResponseDTO.builder().statusCode(ConstantFood.FOOD_SUBSCTRACT_SUCESSFUL.getStatusCode())
						.message(ConstantFood.FOOD_SUBSCTRACT_SUCESSFUL.getMessage()).build();

			} else {
				return ResponseDTO.builder().statusCode(ConstantFood.FOOD_SUBSCTRACT_FAILED.getStatusCode())
						.message(ConstantFood.FOOD_SUBSCTRACT_FAILED.getMessage()).build();
			}
		}
		return ResponseDTO.builder().statusCode(ConstantFood.ENTITY_NOT_FOUND.getStatusCode())
				.message(ConstantFood.ENTITY_NOT_FOUND.getMessage()).object(inventoryFoodDTO).build();
	}

	public void registerTrace(HistoryFoodDTO historyFoodDTO, int amount, String typeOperation, int initialAmount) {
		System.out.println("entra al historial");
		Food foodFromDB = null;
		try {
			foodFromDB = foodRepository.getFoodById(historyFoodDTO.foodId);
			if (foodFromDB != null) {
				int balance = 0;
				HistoryFood historyFood = new HistoryFood();
				if (typeOperation.equals(ConstantFood.INPUT_OPERATION_TYPE.getMessage())) {
					historyFoodDTO.setInput(amount);
					balance = initialAmount + amount;
					historyFood.setInput(amount);
				} else if (typeOperation.equals(ConstantFood.OUTPUT_OPERATION_TYPE.getMessage())) {
					historyFoodDTO.setOutput(amount);
					balance = initialAmount - amount;
					historyFood.setOutput(amount);
				} else {
					historyFoodDTO.setInput(amount);
					balance = amount;
					historyFood.setInput(amount);
				}
				historyFoodDTO.setBalance(balance);

				historyFood.setBalance(balance);
				historyFood.setDate(historyFoodDTO.getDate());
				historyFood.setIcaRegistration(historyFoodDTO.getIcaRegistration());
				historyFood.setFoodId(foodFromDB);
				historyFood.setObservation(historyFoodDTO.getObservation());
				historyFood.setLote(historyFoodDTO.getLote());
				historyFoodRepository.save(historyFood);
			}
		} catch (Exception e) {
			// ToDo how to handle the exception
		}

	}

	public ResponseDTO<Object> getAllFood() {
		List<InventoryFoodDTO> response = new ArrayList<>();
		Iterable<InventoryFood> listInventoryFood = inventoryFoodRepository.findAll();
		InventoryFoodDTO aux;
		for (InventoryFood item : listInventoryFood) {
			Food auxFood = foodRepository.getFoodById(item.getFoodId().getFoodId());
			aux = new InventoryFoodDTO();
			aux.setName(auxFood.getName());
			aux.setInventoryFoodId(item.getInventoryFoodId());
			aux.setFoodId(item.getFoodId().getFoodId());
			aux.setCantidad(item.getCantidad());
			aux.setLote(item.getLote());
			aux.setRegistroIca(item.getRegistroIca());
			aux.setFechaVencimiento(item.getFechaVencimiento());
			aux.setNombreAlmacen(item.getNombreAlmacen());
			aux.setObservation(item.getObservation());
			response.add(aux);
		}
		return ResponseDTO.builder().statusCode(ConstantFood.GET_ALL_FOOD_SUCESSFUL.getStatusCode())
				.message(ConstantFood.GET_ALL_FOOD_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> getAllFoodItems() {
		List<FoodDTO> response = new ArrayList<>();
		Iterable<Food> listInventoryFood = foodRepository.findAll();
		FoodDTO aux;
		for (Food item : listInventoryFood) {
			aux = new FoodDTO();
			aux.setFoodId(item.getFoodId());
			aux.setName(item.getName());
			response.add(aux);
		}
		return ResponseDTO.builder().statusCode(ConstantFood.GET_ALL_FOOD_SUCESSFUL.getStatusCode())
				.message(ConstantFood.GET_ALL_FOOD_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> deleteInventory(Long id) {
		InventoryFood inventory = inventoryFoodRepository.getInventoryFoodById(id);

		if (inventory != null) {
			inventoryFoodRepository.deleteById(id);

			return ResponseDTO.builder().statusCode(ConstantFood.FOOD_DELETED_SUCESSFUL.getStatusCode())
					.message(ConstantFood.FOOD_DELETED_SUCESSFUL.getMessage()).build();
		} else {
			return ResponseDTO.builder().statusCode(ConstantFood.ENTITY_NOT_FOUND.getStatusCode())
					.message(ConstantFood.ENTITY_NOT_FOUND.getMessage()).build();
		}

	}
	
	public ResponseDTO<Object> editFood(FoodDTO foodDTO){
		
		
		
		return ResponseDTO.builder()
				.build();
	}

}
