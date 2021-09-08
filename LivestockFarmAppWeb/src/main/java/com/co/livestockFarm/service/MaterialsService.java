package com.co.livestockFarm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.InventoryMaterialsDTO;
import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.InventoryMaterials;
import com.co.livestockFarm.entity.Materials;
import com.co.livestockFarm.repository.HistoryMaterialsRepository;
import com.co.livestockFarm.repository.InventoryMaterialsRepository;
import com.co.livestockFarm.repository.MaterialsRepository;
import com.co.livestockFarm.util.Constant;
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
		if (materialsDB == null) {
			Materials materials = objectMapper.convertValue(materialsDTO, Materials.class);
			materials.setName(materials.getName().toLowerCase().trim());
			materialsRepository.save(materials);

			return ResponseDTO.builder().statusCode(Constant.MATERIALS_SUCESSFUL.getStatusCode())
					.message(Constant.MATERIALS_SUCESSFUL.getMessage()).build();
		}

		return ResponseDTO.builder().statusCode(Constant.MATERIALS_REPEAT.getStatusCode())
				.message(Constant.MATERIALS_REPEAT.getMessage()).build();
	}

	public ResponseDTO<Object> addMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		InventoryMaterialsDTO inventoryMaterialsDB = getInventoryMaterialsByMaterialsId(
				inventoryMaterialsDTO.getMaterialsIdId());
		Materials materials = new Materials();
		materials.setMaterialsId(inventoryMaterialsDB.getMaterialsId().getMaterialsId());
		InventoryMaterials inventoryMaterials = new InventoryMaterials();
		inventoryMaterials.setInventoryMaterialsid(inventoryMaterialsDB.getInventoryMaterialsid());
		inventoryMaterials.setMaterialsId(materials);
		inventoryMaterials.setAmount(inventoryMaterialsDTO.getAmount() + inventoryMaterialsDB.getAmount());
		inventoryMaterialsRepository.save(inventoryMaterials);

		return ResponseDTO.builder().statusCode(Constant.INVENTORY_MATERIALS_SUCESSFUL.getStatusCode())
				.message(Constant.INVENTORY_MATERIALS_SUCESSFUL.getMessage()).build();
	}

	public ResponseDTO<Object> removeMaterials(InventoryMaterialsDTO inventoryMaterialsDTO) {
		Optional<InventoryMaterials> inventoryMaterials = inventoryMaterialsRepository.findById(inventoryMaterialsDTO.getMaterialsIdId());
		
		if(inventoryMaterials != null) {
			
		}
		
		return ResponseDTO.builder().statusCode(Constant.INVENTORY_MATERIALS_SUCESSFUL.getStatusCode())
				.message(Constant.INVENTORY_MATERIALS_SUCESSFUL.getMessage()).build();
	}

	private MaterialsDTO getMaterialsByName(String name) {
		Materials materials = materialsRepository.getByName(name.toLowerCase());
		if (materials != null) {

			return objectMapper.convertValue(materials, MaterialsDTO.class);
		}

		return null;
	}

	private InventoryMaterialsDTO getInventoryMaterialsByMaterialsId(Long id) {
		InventoryMaterials inventoryMaterials = inventoryMaterialsRepository.getByMaterialsId(id);
		if (inventoryMaterials == null) {
			InventoryMaterials request = new InventoryMaterials();
			Materials materials = new Materials();
			materials.setMaterialsId(id);
			request.setMaterialsId(materials);
			request.setAmount(0L);
			inventoryMaterials = inventoryMaterialsRepository.save(request);
		}

		return objectMapper.convertValue(inventoryMaterials, InventoryMaterialsDTO.class);
	}
}
