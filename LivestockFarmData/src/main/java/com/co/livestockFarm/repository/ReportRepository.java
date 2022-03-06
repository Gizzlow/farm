package com.co.livestockFarm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.livestockFarm.dto.ReportFoodDTO;
import com.co.livestockFarm.dto.ReportMedicineDTO;
import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.entity.HistoryFood;
import com.co.livestockFarm.entity.HistoryMaterials;

public interface ReportRepository extends CrudRepository<HistoryMaterials, Long> {

	@Query(value = "SELECT new com.co.livestockFarm.dto.ReportTreatmentDTO(T.date,L.name,L.type,T.treatment,T.medicineName,T.expirationDate,T.medicineLot,T.icaCode,T.amount,T.medicineType,T.retireTime,T.endTreatment,T.personEncharge) FROM Treatment T JOIN Livestock L ON T.livestockId = L.livestockId WHERE  CAST(T.date as date) BETWEEN :fechaInicial AND :fechaFinal ORDER BY T.date DESC")
	List<ReportTreatmentDTO> getReportTreatment(@Param("fechaInicial") Date fechaInicial,
			@Param("fechaFinal") Date fechaFinal);

	@Query(value = "SELECT new com.co.livestockFarm.dto.ReportMedicineDTO(M.name,M.activeIngredient,M.codeICA,H.date,H.input,H.output,H.residue,H.expirationDate,H.lot) FROM HistoryMedicine H JOIN Medicine M ON H.medicineId = M.medicineId WHERE H.date BETWEEN :fechaInicial AND :fechaFinal ORDER BY H.date DESC")
	List<ReportMedicineDTO> getReportMedicine(@Param("fechaInicial") Date fechaInicial,
			@Param("fechaFinal") Date fechaFinal);

	@Query(value = "SELECT H FROM HistoryMaterials H WHERE CAST(H.date as date) BETWEEN :fechaInicial AND :fechaFinal ORDER BY H.date DESC")
	List<HistoryMaterials> getReportMaterials(@Param("fechaInicial") Date fechaInicial,
			@Param("fechaFinal") Date fechaFinal);

	@Query(value = "SELECT new com.co.livestockFarm.dto.ReportFoodDTO(F.name, H.date, H.input, H.output, H.balance, H.expirationDate, H.nombreAlmacen, H.icaRegistration, H.lote, H.observation) FROM HistoryFood H JOIN Food F ON H.foodId = F.foodId WHERE  CAST(H.date as date) BETWEEN :fechaInicial AND :fechaFinal ORDER BY H.date DESC")
	List<ReportFoodDTO> getReportFood(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}