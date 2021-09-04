package com.co.livestockFarm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.Materials;

public interface MaterialsRepository extends CrudRepository<Materials, Long> {

	@Query(value = "SELECT * FROM Materials WHERE Materials.name =:name")
	Materials getByName(@Param("name") String name);

}