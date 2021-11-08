package com.co.livestockFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.dto.TreatmentDTO;
import com.co.livestockFarm.service.TreatmentService;
import com.co.livestockFarm.util.ConstantFood;

@Controller
@RequestMapping(value = "/treatment")
public class TreatmentController {
	@Autowired
	TreatmentService treatmenService;
	
	@PostMapping(path = "/add")
	@ResponseBody
	public ResponseDTO<Object> addTreatment(@RequestBody TreatmentDTO treatmentDTO) {
		ResponseDTO<Object> responseDTO;
		try {
			responseDTO = treatmenService.addTreatment(treatmentDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).object(treatmentDTO).build();
		}

		return responseDTO;
	}


}
