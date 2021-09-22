package com.co.livestockFarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.LivestockDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.Livestock;
import com.co.livestockFarm.repository.LivestockRepository;
import com.co.livestockFarm.util.ConstantLivestock;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LivestockService {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private LivestockRepository livestockRepository;

	public ResponseDTO<Object> registerLivestock(LivestockDTO ganadoDTO) {
		ResponseDTO<Object> responseDTO;
		if (ganadoDTO.getMotherId() == null) {
			// Is a Mother cow
			Livestock livestock = objectMapper.convertValue(ganadoDTO, Livestock.class);
			livestockRepository.save(livestock);
			responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getStatusCode())
					.message(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getMessage()).object(ganadoDTO).build();
			return responseDTO;
		} else {
			// Search if Mother cow exists
			Livestock initiaLlivestock = livestockRepository.getLivestockById(ganadoDTO.getMotherId().getLivestockId());

			if (initiaLlivestock != null) {
				Livestock livestock = objectMapper.convertValue(ganadoDTO, Livestock.class);
				livestockRepository.save(livestock);
				responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getStatusCode())
						.message(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getMessage()).object(ganadoDTO).build();
				return responseDTO;
			} else {
				//Id provided for mother cow does not exists
				responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.ENTITY_NOT_FOUND.getStatusCode())
						.message(ConstantLivestock.ENTITY_NOT_FOUND.getMessage()).object(ganadoDTO).build();
				return responseDTO;
			}			
		}
	}

}
