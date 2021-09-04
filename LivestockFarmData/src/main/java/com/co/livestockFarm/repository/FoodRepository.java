package com.co.livestockFarm.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.livestockFarm.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Integer>{

}
