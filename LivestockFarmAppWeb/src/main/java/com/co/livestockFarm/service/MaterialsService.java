package com.co.livestockFarm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.HistoryMaterialsDTO;
import com.co.livestockFarm.dto.InventoryMaterialsDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.HistoryMaterials;
import com.co.livestockFarm.entity.InventoryMaterials;
import com.co.livestockFarm.entity.Materials;
import com.co.livestockFarm.repository.HistoryMaterialsRepository;
import com.co.livestockFarm.repository.InventoryMaterialsRepository;
import com.co.livestockFarm.repository.MaterialsRepository;
import com.co.livestockFarm.util.ConstantMaterials;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialsService {

	@Autowired
	MaterialsRepository materialsRepository;

	@Autowired
	InventoryMaterialsRepository inventoryMaterialsRepository;

	@Autowired
	HistoryMaterialsRepository historyMaterialsRepository;

	@Autowired
	ObjectMapper objectMapper;

	public ResponseDTO<Object> registerMaterials(MaterialsDTO materialsDTO) {
		MaterialsDTO materialsDB = getMaterialsByName(materialsDTO.getName().toLowerCase().trim());
		Materials materials = new Materials();
		if (materialsDB == null) {
			materials.setName(materialsDTO.getName().toLowerCase().trim());
			materials = materialsRepository.save(materials);
		} else {
			List<InventoryMaterials> inventoryMaterialsDB = inventoryMaterialsRepository
					.getByMaterialsId(materialsDB.getMaterialsId());
			if (!inventoryMaterialsDB.isEmpty()) {

				return ResponseDTO.builder().statusCode(ConstantMaterials.MATERIALS_REPEAT.getStatusCode())
						.message(ConstantMaterials.MATERIALS_REPEAT.getMessage()).build();
			}
			materials.setMaterialsId(materialsDB.getMaterialsId());
		}
		InventoryMaterials inventoryMaterials = new InventoryMaterials();
		inventoryMaterials.setMaterialsId(materials);
		inventoryMaterials.setAmount(materialsDTO.getAmount());
		inventoryMaterialsRepository.save(inventoryMaterials);
		InventoryMaterialsDTO inventoryMaterialsDTO = new InventoryMaterialsDTO();
		inventoryMaterialsDTO.setMaterialsId(materials.getMaterialsId());
		registerHistorial(inventoryMaterialsDTO, materialsDTO.getAmount(), null, materialsDTO.getAmount());

		return ResponseDTO.builder().statusCode(ConstantMaterials.REGISTER_MATERIALS_SUCESSFUL.getStatusCode())
				.message(ConstantMaterials.REGISTER_MATERIALS_SUCESSFUL.getMessage()).build();
	}

	private MaterialsDTO getMaterialsByName(String name) {
		Materials materials = materialsRepository.getByName(name.toLowerCase());
		if (materials != null) {

			return objectMapper.convertValue(materials, MaterialsDTO.class);
		}

		return null;
	}

	public ResponseDTO<Object> getAllMaterials() {
		List<InventoryMaterialsDTO> response = new ArrayList<>();
		Iterable<InventoryMaterials> listInventoryMaterials = inventoryMaterialsRepository.findAll();
		InventoryMaterialsDTO aux;
		for (InventoryMaterials inventoryMaterials : listInventoryMaterials) {
			aux = new InventoryMaterialsDTO();
			aux.setInventoryMaterialsId(inventoryMaterials.getInventoryMaterialsId());
			aux.setMaterialsId(inventoryMaterials.getMaterialsId().getMaterialsId());
			aux.setAmount(inventoryMaterials.getAmount());
			aux.setName(inventoryMaterials.getMaterialsId().getName());
			response.add(aux);
		}

		return ResponseDTO.builder().statusCode(ConstantMaterials.GET_ALL_MATERIALS_SUCESSFUL.getStatusCode())
				.message(ConstantMaterials.GET_ALL_MATERIALS_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> addMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		Optional<InventoryMaterials> inventoryMaterials = inventoryMaterialsRepository
				.findById(inventoryMaterialsDTO.getInventoryMaterialsId());
		InventoryMaterials inventoryMaterialsDB;
		if (inventoryMaterials.isPresent()) {
			inventoryMaterialsDB = inventoryMaterials.get();
			Long residue = inventoryMaterialsDB.getAmount() + inventoryMaterialsDTO.getAmount();
			inventoryMaterialsDB.setAmount(inventoryMaterialsDB.getAmount() + inventoryMaterialsDTO.getAmount());
			inventoryMaterialsRepository.save(inventoryMaterialsDB);
			inventoryMaterialsDTO.setMaterialsId(inventoryMaterialsDB.getMaterialsId().getMaterialsId());
			registerHistorial(inventoryMaterialsDTO, inventoryMaterialsDTO.getAmount(), null, residue);

			return ResponseDTO.builder().statusCode(ConstantMaterials.ADD_MATERIALS_SUCESSFUL.getStatusCode())
					.message(ConstantMaterials.ADD_MATERIALS_SUCESSFUL.getMessage()).build();
		}
		inventoryMaterialsDB = new InventoryMaterials();
		Materials materials = new Materials();
		materials.setMaterialsId(inventoryMaterialsDTO.getMaterialsId());
		inventoryMaterialsDB.setMaterialsId(materials);
		inventoryMaterialsDB.setAmount(inventoryMaterialsDTO.getAmount());
		inventoryMaterialsRepository.save(inventoryMaterialsDB);
		registerHistorial(inventoryMaterialsDTO, inventoryMaterialsDTO.getAmount(), null,
				inventoryMaterialsDB.getAmount() + inventoryMaterialsDTO.getAmount());

		return ResponseDTO.builder().statusCode(ConstantMaterials.ADD_MATERIALS_SUCESSFUL.getStatusCode())
				.message(ConstantMaterials.ADD_MATERIALS_SUCESSFUL.getMessage()).build();

	}

	public ResponseDTO<Object> removeMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		Optional<InventoryMaterials> inventoryMaterials = inventoryMaterialsRepository
				.findById(inventoryMaterialsDTO.getInventoryMaterialsId());
		InventoryMaterials inventoryMaterialsDB;
		if (inventoryMaterials.isPresent()) {
			inventoryMaterialsDB = inventoryMaterials.get();
			Long residue = inventoryMaterialsDB.getAmount() - inventoryMaterialsDTO.getAmount();
			inventoryMaterialsDB.setAmount(inventoryMaterialsDB.getAmount() - inventoryMaterialsDTO.getAmount());
			if (inventoryMaterialsDB.getAmount() >= 0) {
				inventoryMaterialsRepository.save(inventoryMaterialsDB);
				inventoryMaterialsDTO.setMaterialsId(inventoryMaterialsDB.getMaterialsId().getMaterialsId());
				registerHistorial(inventoryMaterialsDTO, null, inventoryMaterialsDTO.getAmount(), residue);

				return ResponseDTO.builder().statusCode(ConstantMaterials.REMOVE_MATERIALS_SUCESSFUL.getStatusCode())
						.message(ConstantMaterials.REMOVE_MATERIALS_SUCESSFUL.getMessage()).build();
			}

			return ResponseDTO.builder().statusCode(ConstantMaterials.REMOVE_MATERIALS_FAIL_MOUNT.getStatusCode())
					.message(ConstantMaterials.REMOVE_MATERIALS_FAIL_MOUNT.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMaterials.REMOVE_MATERIALS_FAIL.getStatusCode())
				.message(ConstantMaterials.REMOVE_MATERIALS_FAIL.getMessage()).build();
	}

	public ResponseDTO<Object> deleteMaterials(Long id) {
		InventoryMaterials inventoryMaterials = inventoryMaterialsRepository.getByInventoryMaterialsId(id);
		if (inventoryMaterials != null) {
			inventoryMaterialsRepository.delete(inventoryMaterials);

			return ResponseDTO.builder().statusCode(ConstantMaterials.DELETE_MATERIALS_SUCESSFUL.getStatusCode())
					.message(ConstantMaterials.DELETE_MATERIALS_SUCESSFUL.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(ConstantMaterials.DELETE_MATERIALS_FAIL.getStatusCode())
				.message(ConstantMaterials.DELETE_MATERIALS_FAIL.getMessage()).build();
	}

	private void registerHistorial(InventoryMaterialsDTO inventoryMaterialsDTO, Long input, Long output, Long residue) {
		HistoryMaterials historyMaterials = new HistoryMaterials();
		Materials materials = new Materials();
		materials.setMaterialsId(inventoryMaterialsDTO.getMaterialsId());
		historyMaterials.setMaterialsId(materials);
		historyMaterials.setDate(new Date());
		historyMaterials.setObservation(inventoryMaterialsDTO.getObservation());
		historyMaterials.setInput(input);
		historyMaterials.setOutput(output);
		historyMaterials.setResidue(residue);
		historyMaterialsRepository.save(historyMaterials);
	}

	public void getReport(HistoryMaterialsDTO historyMaterialsDTO) throws ParseException {
		if (historyMaterialsDTO.getInitDate() == null || historyMaterialsDTO.getEndDate() == null) {

		}
		String sDate1 = historyMaterialsDTO.getInitDate();
		Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
		String sDate2 = historyMaterialsDTO.getEndDate();
		Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
		List<HistoryMaterials> response = historyMaterialsRepository.getReport(date1, date2);
		System.out.print(response.toString());
	}
}
