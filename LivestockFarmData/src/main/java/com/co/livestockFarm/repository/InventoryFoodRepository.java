package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.InventoryFood;

public interface InventoryFoodRepository extends CrudRepository<InventoryFood, Long>{

	@Query(value = "SELECT inventory FROM InventoryFood inventory WHERE inventory.inventoryFoodId =:inventoryFoodId")
	InventoryFood getInventoryFoodById(@Param("inventoryFoodId") long inventoryFoodId);

}
