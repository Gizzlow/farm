package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.InventoryMedicine;

public interface InventoryMedicineRepository extends CrudRepository<InventoryMedicine, Long> {

	@Query(value = "SELECT medicine FROM InventoryMedicine medicine WHERE medicine.inventoryMedicineId =:inventoryMedicineId")
	InventoryMedicine getInventoryMedicineById(@Param("inventoryMedicineId") Long inventoryMedicineId);

}
