package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.InventoryMaterials;

public interface InventoryMaterialsRepository extends CrudRepository<InventoryMaterials, Long> {

	@Query(value = "SELECT im FROM InventoryMaterials im WHERE im.materialsId.materialsId =:materialsId")
	InventoryMaterials getByMaterialsId(@Param("materialsId") Long materialsId);

}
