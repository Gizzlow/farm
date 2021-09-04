package com.co.livestockFarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.MaterialsDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.Materials;
import com.co.livestockFarm.repository.MaterialsRepository;
import com.co.livestockFarm.util.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialsService {

	@Autowired
	MaterialsRepository materialsRepository;

	@Autowired
	ObjectMapper objectMapper;

	public ResponseDTO<Object> registerMaterials(MaterialsDTO materialsDTO) {
		ResponseDTO<Object> response = new ResponseDTO<>();
		MaterialsDTO materialsDB = getMaterialsByName(materialsDTO.getName());
		if (materialsDB == null) {
			Materials materials = objectMapper.convertValue(materialsDTO, Materials.class);
			materialsRepository.save(materials);
			response.setStatusCode(Constant.MATERIALS_SUCESSFUL.getStatusCode());
			response.setMessage(Constant.MATERIALS_SUCESSFUL.getMessage());

			return response;
		}
		response.setStatusCode(Constant.MATERIALS_REPEAT.getStatusCode());
		response.setMessage(Constant.MATERIALS_REPEAT.getMessage());

		return response;
	}

	public MaterialsDTO getMaterialsByName(String name) {
		Materials materials = materialsRepository.getByName(name);
		if (materials != null) {

			return objectMapper.convertValue(materials, MaterialsDTO.class);
		}

		return null;
	}
}
