package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.livestockFarm.entity.Livestock;

public interface LivestockRepository extends CrudRepository<Livestock, Long> {

	@Query(value = "SELECT livestock FROM Livestock livestock WHERE livestock.motherId.livestockId =:livestockId")
	Livestock getLivestockById(long livestockId);

}
