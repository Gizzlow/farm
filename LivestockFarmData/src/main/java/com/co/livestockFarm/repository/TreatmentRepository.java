package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.livestockFarm.entity.Treatment;

public interface TreatmentRepository extends CrudRepository<Treatment, Long>{
	@Query(value = "SELECT treatment FROM Treatment treatment WHERE treatment.id =:treatmentId")
	Treatment getTreatmentById(long treatmentId);
}
