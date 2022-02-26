package com.co.livestockFarm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.dto.TreatmentDTO;
import com.co.livestockFarm.service.ReportService;
import com.co.livestockFarm.service.TreatmentService;
import com.co.livestockFarm.util.ConstantFood;

@Controller
@RequestMapping(value = "/treatment")
public class TreatmentController {
	
	@Autowired
	private TreatmentService treatmenService;
	
	@Autowired
	private ReportService reportService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseDTO<Object> addTreatment(TreatmentDTO treatmentDTO) {
		ResponseDTO<Object> responseDTO;
		try {
			responseDTO = treatmenService.addTreatment(treatmentDTO);
		} catch (Exception e) {

			return ResponseDTO.builder().statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage()).object(treatmentDTO).build();
		}

		return responseDTO;
	}
	
	@GetMapping("/getreport")
	@ResponseBody
	public ResponseDTO<Object> getreport() {
		ResponseDTO<Object> responseDTO = null;
		try {
			Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
			reportService.reportTreatment(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), new Date());
		} catch (Exception e) {

			return ResponseDTO.builder()
					.statusCode(ConstantFood.ERROR_FATAL.getStatusCode())
					.message(ConstantFood.ERROR_FATAL.getMessage())
					.object(null).build();
		}

		return responseDTO;
	}
	

}
