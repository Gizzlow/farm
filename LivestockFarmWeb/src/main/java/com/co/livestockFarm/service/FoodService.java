package com.co.livestockFarm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.FoodDTO;
import com.co.livestockFarm.entity.Food;
import com.co.livestockFarm.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	public FoodDTO registerFood(int foodId) {
		FoodDTO response = new FoodDTO();
		Optional<Food> initialFood = foodRepository.findById(foodId);
		if (!initialFood.isPresent()) {
			response.name = "No está";
		}else {
			response.name = "Está";
		}
		return response;
	}

}
