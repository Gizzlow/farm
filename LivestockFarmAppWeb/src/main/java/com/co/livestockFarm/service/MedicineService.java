package com.co.livestockFarm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.InventoryMaterialsDTO;
import com.co.livestockFarm.dto.InventoryMedicineDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.MedicineDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.InventoryMaterials;
import com.co.livestockFarm.entity.InventoryMedicine;
import com.co.livestockFarm.entity.Medicine;
import com.co.livestockFarm.repository.HistoryMedicineRepository;
import com.co.livestockFarm.repository.InventoryMedicineRepository;
import com.co.livestockFarm.repository.MedicineRepository;
import com.co.livestockFarm.util.ConstantMedicine;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;

	@Autowired
	InventoryMedicineRepository inventoryMedicineRepository;

	@Autowired
	HistoryMedicineRepository historyMedicineRepository;

	@Autowired
	ObjectMapper objectMapper;

	public ResponseDTO<Object> registerMedicine(MedicineDTO medicineDTO) {
		MedicineDTO medicineDB = getMedicineByName(medicineDTO.getName().toLowerCase().trim());
		if (medicineDB == null) {
			Medicine medicine = objectMapper.convertValue(medicineDTO, Medicine.class);
			medicineRepository.save(medicine);

			return ResponseDTO.builder().statusCode(ConstantMedicine.REGISTER_MEDICINE_SUCESSFUL.getStatusCode())
					.message(ConstantMedicine.REGISTER_MEDICINE_SUCESSFUL.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.MEDICINE_REPEAT.getStatusCode())
				.message(ConstantMedicine.MEDICINE_REPEAT.getMessage()).build();
	}

	private MedicineDTO getMedicineByName(String name) {
		Medicine medicine = medicineRepository.getByName(name.toLowerCase());
		if (medicine != null) {

			return objectMapper.convertValue(medicine, MedicineDTO.class);
		}

		return null;
	}

	public ResponseDTO<Object> getAllMedicines() {
		List<InventoryMedicineDTO> response = new ArrayList<>();
		Iterable<InventoryMedicine> listInventoryMedicine = inventoryMedicineRepository.findAll();
		InventoryMedicineDTO aux;
		Medicine medicineDB;
		for (InventoryMedicine inventoryMedicine : listInventoryMedicine) {
			aux = new InventoryMedicineDTO();
			Optional<Medicine> medicine = medicineRepository
					.findById(inventoryMedicine.getMedicineId().getMedicineId());
			medicineDB = medicine.get();
			aux.setName(medicineDB.getName());
			aux.setActiveIngredient(medicineDB.getActiveIngredient());
			aux.setExpirationDate(inventoryMedicine.getExpirationDate());
			aux.setAmount(inventoryMedicine.getAmount());
			aux.setMeasurementUnit(medicineDB.getMeasurementUnit());
			response.add(aux);
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getStatusCode())
				.message(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> addMedicines(InventoryMedicineDTO inventoryMedicineDTO) {
		Optional<Medicine> medicine = medicineRepository.findById(inventoryMedicineDTO.getMedicineId());
		if(medicine.isPresent()) {
			InventoryMedicine inventoryMedicine = new InventoryMedicine();
			Medicine medicineAux = new Medicine();
			medicineAux.setMedicineId(inventoryMedicineDTO.getMedicineId());
			inventoryMedicine.setMedicineId(medicineAux);
			inventoryMedicine.setExpirationDate(inventoryMedicineDTO.getExpirationDate());
			inventoryMedicine.setAmount(inventoryMedicineDTO.getAmount());
			inventoryMedicine.setLot(inventoryMedicineDTO.getLot());
			inventoryMedicineRepository.save(inventoryMedicine);
			
			return ResponseDTO.builder()
					.statusCode(ConstantMedicine.MEDICINE_ADD.getStatusCode())
					.message(ConstantMedicine.MEDICINE_ADD.getMessage())
					.build();
		}
		
		return ResponseDTO.builder()
				.statusCode(ConstantMedicine.MEDICINE_ADD_ERROR.getStatusCode())
				.message(ConstantMedicine.MEDICINE_ADD_ERROR.getMessage())
				.build();
	}

}
