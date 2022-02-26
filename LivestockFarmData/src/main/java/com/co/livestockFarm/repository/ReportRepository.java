package com.co.livestockFarm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.entity.HistoryMaterials;

public interface ReportRepository extends CrudRepository<HistoryMaterials, Long> {

	// @Query(value = "SELECT historyMaterials FROM HistoryMaterials
	// historyMaterials WHERE historyMaterials.date BETWEEN :fechaInicial AND
	// :fechaFinal")
	// List<HistoryMaterials> getReportTreatment(@Param("fechaInicial") Date
	// fechaInicial, @Param("fechaFinal") Date fechaFinal);

	@Query(value = "SELECT new com.co.livestockFarm.dto.ReportTreatmentDTO(T.date,L.name,L.type,T.treatment,T.medicineName,T.expirationDate,T.medicineLot,T.icaCode,T.amount,T.medicineType,T.retireTime,T.endTreatment,T.personEncharge) FROM Treatment T JOIN Livestock L ON T.livestockId = L.livestockId WHERE  CAST(T.date as date) BETWEEN :fechaInicial AND :fechaFinal")
	List<ReportTreatmentDTO> getReportTreatment(@Param("fechaInicial") Date fechaInicial,
			@Param("fechaFinal") Date fechaFinal);

}

//