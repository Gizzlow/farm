package com.co.livestockFarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.dto.ResponseDTO;
import com.co.livestockFarm.entity.HistoryFood;
import com.co.livestockFarm.entity.HistoryMaterials;
import com.co.livestockFarm.entity.HistoryMedicine;
import com.co.livestockFarm.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public List<ReportTreatmentDTO> reportTreatment(Date fechaInicial, Date fechafinal) {

		List<ReportTreatmentDTO> response = reportRepository.getReportTreatment(fechaInicial, fechafinal);
		System.out.println(response.toString());
		return response;
	}

	public List<HistoryMedicine> reportMedicine(Date fechaInicial, Date fechafinal) {

		List<HistoryMedicine> response = reportRepository.getReportMedicine(fechaInicial, fechafinal);
		System.out.println(response.toString());
		return response;
	}

	public List<HistoryMaterials> reportMaterials(Date fechaInicial, Date fechafinal) {

		List<HistoryMaterials> response = reportRepository.getReportMaterials(fechaInicial, fechafinal);
		System.out.println(response.toString());
		return response;
	}

	public List<HistoryFood> reportFood(Date fechaInicial, Date fechafinal) {

		List<HistoryFood> response = reportRepository.getReportFood(fechaInicial, fechafinal);
		System.out.println(response.toString());
		return response;
	}

}
