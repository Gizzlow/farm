package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.livestockFarm.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Integer>{

	@Query(value = "SELECT food FROM Food food WHERE food.name =:name")
	Food getMaterialByName(String name);

	@Query(value = "SELECT food FROM Food food WHERE food.id =:foodId")
	Food getFoodById(int foodId);

}
