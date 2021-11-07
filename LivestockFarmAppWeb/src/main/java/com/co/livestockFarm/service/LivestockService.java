package com.co.livestockFarm.service;

import java.util.ArrayList;
import java.util.List;

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
			livestock.setActive(true);
			livestockRepository.save(livestock);
			responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getStatusCode())
					.message(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getMessage()).object(ganadoDTO).build();
			return responseDTO;
		} else {
			// Search if Mother cow exists
			Livestock initiaLlivestock = livestockRepository.getLivestockById(ganadoDTO.getMotherId());

			if (initiaLlivestock != null) {
//				Livestock livestock = objectMapper.convertValue(ganadoDTO, Livestock.class);

				Livestock livestock = new Livestock();				
				livestock.setMotherId(initiaLlivestock);				
				livestock.setActive(true);
				
				if(ganadoDTO.getName() != null)
					livestock.setName(ganadoDTO.getName());
				if(ganadoDTO.getType() != null)
					livestock.setType(ganadoDTO.getType());
				if(ganadoDTO.getObservation() != null)
					livestock.setObservation(ganadoDTO.getObservation());

				livestockRepository.save(livestock);
				responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getStatusCode())
						.message(ConstantLivestock.LIVESTOCK_SUCCESSFUL.getMessage()).object(ganadoDTO).build();
				return responseDTO;
			} else {
				// Id provided for mother cow does not exists
				responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.ENTITY_NOT_FOUND.getStatusCode())
						.message(ConstantLivestock.ENTITY_NOT_FOUND.getMessage()).object(ganadoDTO).build();
				return responseDTO;
			}
		}
	}

	public ResponseDTO<Object> getAllLivestock() {
		List<LivestockDTO> response = new ArrayList<>();
		Iterable<Livestock> entities = livestockRepository.findAll();
		LivestockDTO aux;
		for (Livestock item : entities) {
			aux = new LivestockDTO();
			aux.setActive(item.isActive());
			aux.setLivestockId(item.getLivestockId());
			if (item.getMotherId() != null)
				aux.setMotherId(item.getMotherId().getLivestockId());
			aux.setName(item.getName());
			aux.setObservation(item.getObservation());
			aux.setType(item.getType());
			response.add(aux);
		}
		return ResponseDTO.builder().statusCode(ConstantLivestock.GET_ALL_LIVESTOCK_SUCESSFUL.getStatusCode())
				.message(ConstantLivestock.GET_ALL_LIVESTOCK_SUCESSFUL.getMessage()).object(response).build();
	}

	public ResponseDTO<Object> editLiveStock(LivestockDTO ganado) {
		ResponseDTO<Object> responseDTO;
		Livestock entity = livestockRepository.getLivestockById(ganado.getLivestockId());		
		if(entity != null) {
			
			if(ganado.getName() != null)
				entity.setName(ganado.getName());
			if(ganado.getType() != null)
				entity.setType(ganado.getType());
			if(ganado.getObservation() != null)
				entity.setObservation(ganado.getObservation());
			if(ganado.getActive() != null)
				entity.setActive(ganado.getActive());
			livestockRepository.save(entity);
			responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.LIVESTOCK_EDIT_SUCCESSFUL.getStatusCode())
					.message(ConstantLivestock.LIVESTOCK_EDIT_SUCCESSFUL.getMessage()).object(ganado).build();
		}else {
			responseDTO = ResponseDTO.builder().statusCode(ConstantLivestock.ENTITY_NOT_FOUND.getStatusCode())
					.message(ConstantLivestock.ENTITY_NOT_FOUND.getMessage()).object(ganado).build();
		}
		return responseDTO;
	}

}
