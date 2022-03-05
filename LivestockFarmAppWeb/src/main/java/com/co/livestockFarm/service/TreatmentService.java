package com.co.livestockFarm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.dto.TreatmentDTO;
import com.co.livestockFarm.entity.InventoryMedicine;
import com.co.livestockFarm.entity.Livestock;
import com.co.livestockFarm.entity.Treatment;
import com.co.livestockFarm.repository.InventoryMedicineRepository;
import com.co.livestockFarm.repository.LivestockRepository;
import com.co.livestockFarm.repository.TreatmentRepository;
import com.co.livestockFarm.util.ConstantTreatment;

@Service
public class TreatmentService {

	@Autowired
	private TreatmentRepository treatmentRepository;
	@Autowired
	private InventoryMedicineRepository inventoryMedicineRepository;
	@Autowired
	private LivestockRepository liveStockRepository;

	public ResponseDTO<Object> addTreatment(TreatmentDTO treatmentDTO) {
		InventoryMedicine medicineEntity = inventoryMedicineRepository
				.getInventoryMedicineById(treatmentDTO.getInventoryId());
		ResponseDTO<Object> responseDTO;
		if (treatmentDTO.getAmount() <= medicineEntity.getAmount()) {

			Treatment treatment = new Treatment();
			Livestock liveStock = liveStockRepository.getLivestockById(treatmentDTO.getLivestockId());

			
			int month = Integer.parseInt(treatmentDTO.getTimeRetirement())/30;
			int days = Integer.parseInt(treatmentDTO.getTimeRetirement())%30;


			treatment.setLivestockId(liveStock);
			treatment.setDate(treatmentDTO.getDate());
			treatment.setExpirationDate(treatmentDTO.getExpirationDate());
			treatment.setMedicineLot(treatmentDTO.getMedicineLot());
			treatment.setAmount(treatmentDTO.getAmount());
			treatment.setTreatment(treatmentDTO.getTreatment());
			treatment.setEndTreatment(treatmentDTO.getEndTreatment());
			treatment.setMedicineName(treatmentDTO.getMedicineName());
			treatment.setIcaCode(treatmentDTO.getIcaCode());
			treatment.setMedicineType(treatmentDTO.getType());
			treatment.setRetireTime(month+"-"+days);
			treatment.setPersonEncharge(treatmentDTO.getPersonEncharge());

			treatmentRepository.save(treatment);

			medicineEntity.setAmount(medicineEntity.getAmount() - treatmentDTO.getAmount());
			inventoryMedicineRepository.save(medicineEntity);

			responseDTO = ResponseDTO.builder()
					.statusCode(ConstantTreatment.REMOVE_INVENTORY_MEDICINE_AMOUNT_SUCCESS.getStatusCode())
					.message(ConstantTreatment.REMOVE_INVENTORY_MEDICINE_AMOUNT_SUCCESS.getMessage())
					.object(treatmentDTO).build();
		} else {
			responseDTO = ResponseDTO.builder()
					.statusCode(ConstantTreatment.REMOVE_INVENTORY_MEDICINE_AMOUNT_FAIL.getStatusCode())
					.message(ConstantTreatment.REMOVE_INVENTORY_MEDICINE_AMOUNT_FAIL.getMessage()).object(treatmentDTO)
					.build();
		}
		return responseDTO;
	}

}
