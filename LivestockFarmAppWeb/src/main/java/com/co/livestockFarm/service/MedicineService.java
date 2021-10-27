package com.co.livestockFarm.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.co.livestockFarm.dto.InventoryMedicineDTO;
import com.co.livestockFarm.dto.MedicineDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.HistoryMedicine;
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
		List<MedicineDTO> response = new ArrayList<>();
		Iterable<Medicine> listMedicine = medicineRepository.findAll();
		MedicineDTO aux;
		for (Medicine medicine : listMedicine) {
			aux = new MedicineDTO();
			aux.setMedicineId(medicine.getMedicineId());
			aux.setName(medicine.getName());
			aux.setCodeICA(medicine.getCodeICA());
			aux.setTantamount(medicine.getTantamount());
			aux.setGroupe(medicine.getGroupe());
			aux.setActiveIngredient(medicine.getActiveIngredient());
			aux.setMeasurementUnit(medicine.getMeasurementUnit());
			aux.setType(medicine.getType());
			response.add(aux);
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getStatusCode())
				.message(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> getAllInventoryMedicines() {
		List<InventoryMedicineDTO> response = new ArrayList<>();
		Iterable<InventoryMedicine> listInventoryMedicine = inventoryMedicineRepository.findAll();
		InventoryMedicineDTO aux;
		Medicine medicineDB;
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		for (InventoryMedicine inventoryMedicine : listInventoryMedicine) {
			aux = new InventoryMedicineDTO();
			Optional<Medicine> medicine = medicineRepository
					.findById(inventoryMedicine.getMedicineId().getMedicineId());
			medicineDB = medicine.get();
			aux.setInventoryMedicineId(inventoryMedicine.getInventoryMedicineId());
			aux.setName(medicineDB.getName());
			aux.setActiveIngredient(medicineDB.getActiveIngredient());
			aux.setExpiration(df.format(inventoryMedicine.getExpirationDate()));
			aux.setAmount(inventoryMedicine.getAmount());
			aux.setMeasurementUnit(medicineDB.getMeasurementUnit());
			response.add(aux);
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getStatusCode())
				.message(ConstantMedicine.GET_ALL_MEDICINES_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> addMedicines(InventoryMedicineDTO inventoryMedicineDTO) throws ParseException {
		Optional<Medicine> medicine = medicineRepository.findById(inventoryMedicineDTO.getMedicineId());
		if (medicine.isPresent()) {
			InventoryMedicine inventoryMedicine = new InventoryMedicine();
			Medicine medicineAux = new Medicine();
			medicineAux.setMedicineId(inventoryMedicineDTO.getMedicineId());
			inventoryMedicine.setMedicineId(medicineAux);
			inventoryMedicine
					.setExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse(inventoryMedicineDTO.getExpiration()));
			inventoryMedicine.setAmount(inventoryMedicineDTO.getAmount());
			inventoryMedicine.setLot(inventoryMedicineDTO.getLot());
			inventoryMedicineRepository.save(inventoryMedicine);

			return ResponseDTO.builder().statusCode(ConstantMedicine.MEDICINE_ADD.getStatusCode())
					.message(ConstantMedicine.MEDICINE_ADD.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.MEDICINE_ADD_ERROR.getStatusCode())
				.message(ConstantMedicine.MEDICINE_ADD_ERROR.getMessage()).build();
	}

	public ResponseDTO<Object> getMedicineById(Long id) {
		Optional<Medicine> medicineDB = medicineRepository.findById(id);
		if (medicineDB.isPresent()) {
			Medicine medicine = medicineDB.get();

			return ResponseDTO.builder().statusCode(ConstantMedicine.GET_INVENTORY_BY_ID.getStatusCode())
					.message(ConstantMedicine.GET_INVENTORY_BY_ID.getMessage()).object(medicine).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.GET_INVENTORY_BY_ID_ERROR.getStatusCode())
				.message(ConstantMedicine.GET_INVENTORY_BY_ID_ERROR.getMessage()).build();
	}

	public ResponseDTO<Object> removeMedicines(InventoryMedicineDTO inventoryMedicineDTO) {
		Optional<InventoryMedicine> inventoryMedicineDB = inventoryMedicineRepository
				.findById(inventoryMedicineDTO.getInventoryMedicineId());
		if (inventoryMedicineDB.isPresent()) {
			InventoryMedicine inventoryMedicine = inventoryMedicineDB.get();
			Long residue = inventoryMedicine.getAmount() - inventoryMedicineDTO.getAmount();
			inventoryMedicine.setAmount(inventoryMedicine.getAmount() - inventoryMedicineDTO.getAmount());
			if (inventoryMedicine.getAmount() >= 0) {
				inventoryMedicineRepository.save(inventoryMedicine);
				inventoryMedicineDTO.setMedicineId(inventoryMedicine.getMedicineId().getMedicineId());
				registerHistorial(inventoryMedicineDTO, null, inventoryMedicineDTO.getAmount(), residue);

				return ResponseDTO.builder().statusCode(ConstantMedicine.REMOVE_MEDICINE_SUCESSFUL.getStatusCode())
						.message(ConstantMedicine.REMOVE_MEDICINE_SUCESSFUL.getMessage()).build();
			}

			return ResponseDTO.builder().statusCode(ConstantMedicine.REMOVE_MEDICINE_AMOUNT_FAIL.getStatusCode())
					.message(ConstantMedicine.REMOVE_MEDICINE_AMOUNT_FAIL.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.REMOVE_MEDICINE_ERROR.getStatusCode())
				.message(ConstantMedicine.REMOVE_MEDICINE_ERROR.getMessage()).build();
	}

	public ResponseDTO<Object> deleteMedicine(Long id) {
		Optional<InventoryMedicine> inventoryMedicineDB = inventoryMedicineRepository.findById(id);
		if (inventoryMedicineDB.isPresent()) {
			inventoryMedicineRepository.deleteById(inventoryMedicineDB.get().getInventoryMedicineId());

			return ResponseDTO.builder().statusCode(ConstantMedicine.DELETE_MEDICINE_SUCESSFUL.getStatusCode())
					.message(ConstantMedicine.DELETE_MEDICINE_SUCESSFUL.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMedicine.DELETE_MEDICINE_ERROR.getStatusCode())
				.message(ConstantMedicine.DELETE_MEDICINE_ERROR.getMessage()).build();
	}

	private void registerHistorial(InventoryMedicineDTO inventoryMedicineDTO, Long input, Long output, Long residue) {
		HistoryMedicine historyMedicine = new HistoryMedicine();
		Medicine medicine = new Medicine();
		medicine.setMedicineId(inventoryMedicineDTO.getMedicineId());
		historyMedicine.setMedicineId(medicine);
		historyMedicine.setDate(new Date());
		historyMedicine.setObservation(inventoryMedicineDTO.getObservation());
		historyMedicine.setInput(input);
		historyMedicine.setOutput(output);
		historyMedicine.setResidue(residue);
		historyMedicineRepository.save(historyMedicine);
	}

}
