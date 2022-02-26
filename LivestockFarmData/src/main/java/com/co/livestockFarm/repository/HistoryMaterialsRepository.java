package com.co.livestockFarm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.entity.HistoryMaterials;

public interface HistoryMaterialsRepository extends CrudRepository<HistoryMaterials, Long> {

	@Query(value = "SELECT historyMaterials FROM HistoryMaterials historyMaterials WHERE historyMaterials.date BETWEEN :fechaInicial AND :fechaFinal")
	List<HistoryMaterials> getReport(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}