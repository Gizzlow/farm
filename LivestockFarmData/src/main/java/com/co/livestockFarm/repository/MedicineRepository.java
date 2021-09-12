package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long>{

	@Query(value = "SELECT medicine FROM Medicine medicine WHERE medicine.name =:name")
	Medicine getByName(@Param("name") String name);
	
}
