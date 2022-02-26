package com.co.livestockFarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public ResponseDTO<Object> reportTreatment(Date fechaInicial, Date fechafinal) {

		List<ReportTreatmentDTO> response = reportRepository.getReportTreatment(fechaInicial, fechafinal);
		System.out.println(response.toString());
		return null;
	}

}
