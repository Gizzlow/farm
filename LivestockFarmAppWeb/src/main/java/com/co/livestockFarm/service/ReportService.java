package com.co.livestockFarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.livestockFarm.dto.ReportFoodDTO;
import com.co.livestockFarm.dto.ReportMaterialsDTO;
import com.co.livestockFarm.dto.ReportMedicineDTO;
import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public List<ReportTreatmentDTO> reportTreatment(Date fechaInicial, Date fechafinal) {
		List<ReportTreatmentDTO> response = reportRepository.getReportTreatment(fechaInicial, fechafinal);

		return response;
	}

	public List<ReportMedicineDTO> reportMedicine(Date fechaInicial, Date fechafinal) {
		List<ReportMedicineDTO> response = reportRepository.getReportMedicine(fechaInicial, fechafinal);

		return response;
	}

	public List<ReportMaterialsDTO> reportMaterials(Date fechaInicial, Date fechafinal) {
		List<ReportMaterialsDTO> response = reportRepository.getReportMaterials(fechaInicial, fechafinal);

		return response;
	}

	public List<ReportFoodDTO> reportFood(Date fechaInicial, Date fechafinal) {
		List<ReportFoodDTO> response = reportRepository.getReportFood(fechaInicial, fechafinal);

		return response;
	}
}
