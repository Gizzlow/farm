package com.co.livestockFarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.entity.Materials;
import com.co.livestockFarm.repository.MaterialsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialsService {
	
	@Autowired
	MaterialsRepository materialsRepository;
	
	@Autowired
	ObjectMapper objectMapper;

	public void registerMaterials(MaterialsDTO materialsDTO) {
		Materials materials = objectMapper.convertValue(materialsDTO, Materials.class);
 		materialsRepository.save(materials);
	}
	
	public void getMaterialsByName(String name) {
		materialsRepository.getByName(name);
	}
}
