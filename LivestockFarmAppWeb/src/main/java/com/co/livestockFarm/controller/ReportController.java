package com.co.livestockFarm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.livestockFarm.dto.ReportTreatmentDTO;
import com.co.livestockFarm.service.ReportService;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
	@Autowired
	ReportService reportService;

	@PostMapping(path = "/treatment")
	@ResponseBody
	public void generateTreatmentReport(@RequestBody ReportTreatmentDTO treatment) {
		Workbook workbook = new XSSFWorkbook();
//
		Sheet sheet = workbook.createSheet("TreatmentReport");
		defineColumns(sheet);
		
		Date initialDate = null;
		Date finalDate = null;
		
		try {
			initialDate = new SimpleDateFormat("dd/MM/yyyy").parse(treatment.getDate());
			finalDate = new SimpleDateFormat("dd/MM/yyyy").parse(treatment.getName());
		}catch (Exception e) {
			
		}
		List<ReportTreatmentDTO> results = reportService.reportTreatment(initialDate, finalDate);
		
		String fileLocation = createTreatmentReport(sheet, workbook);
		System.out.println(fileLocation);
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@PostMapping(path = "/medicine")
	@ResponseBody
	public void generateMedicineReport() {
		Workbook workbook = new XSSFWorkbook();
//
		Sheet sheet = workbook.createSheet("MedicineReport");
		defineMedicinceColumns(sheet);

		String fileLocation = createMedicinetReport(sheet, workbook);
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@PostMapping(path = "/food")
	@ResponseBody
	public void generateFoodReport() {
		Workbook workbook = new XSSFWorkbook();
//
		Sheet sheet = workbook.createSheet("FodReport");
		defineFoodColumns(sheet);

		String fileLocation = createFoodReport(sheet, workbook);
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void defineColumns(Sheet sheet) {
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 2000);
		sheet.setColumnWidth(13, 2000);
		sheet.setColumnWidth(14, 2000);
		sheet.setColumnWidth(15, 2000);
		sheet.setColumnWidth(16, 2000);
		sheet.setColumnWidth(17, 2000);
		sheet.setColumnWidth(18, 4000);
		sheet.setColumnWidth(19, 4000);
		sheet.setColumnWidth(20, 2500);
		sheet.setColumnWidth(21, 2500);
		sheet.setColumnWidth(22, 2500);
		sheet.setColumnWidth(23, 7500);
	}
	public void defineMedicinceColumns(Sheet sheet) {
		sheet.setColumnWidth(0, 10000); //Pro
		sheet.setColumnWidth(1, 10000); //Prin
		sheet.setColumnWidth(2, 5000); //Pres
		sheet.setColumnWidth(3, 000); //Regi
		sheet.setColumnWidth(4, 2000); //Anio
		sheet.setColumnWidth(5, 2000); //Mes
		sheet.setColumnWidth(6, 2000); //Dia
		sheet.setColumnWidth(7, 5000); //Entran
		sheet.setColumnWidth(8, 5000); //Salen
		sheet.setColumnWidth(9, 2000); //Anio
		sheet.setColumnWidth(10, 2000); //Mes
		sheet.setColumnWidth(11, 2000); //Dia
		sheet.setColumnWidth(12, 7000); //Lote
		sheet.setColumnWidth(13, 10000); //Saldo
	}
	public void defineFoodColumns(Sheet sheet) {
		sheet.setColumnWidth(0, 10000); //Pro
		sheet.setColumnWidth(1, 2000); //Anio
		sheet.setColumnWidth(2, 2000); //Mes
		sheet.setColumnWidth(3, 2000); //Dia
		sheet.setColumnWidth(4, 7000); //Entra
		sheet.setColumnWidth(5, 7000); //Sali
		sheet.setColumnWidth(6, 7000); //Saldo
		sheet.setColumnWidth(7, 10000); //VTO
		sheet.setColumnWidth(8, 10000); //Datos
		sheet.setColumnWidth(9, 10000); //ICA
		sheet.setColumnWidth(10, 7000); //Lote
		sheet.setColumnWidth(11, 10000); //Dia
	}
	public void defineHeaders(Cell headerCell, Row header, CellStyle headerStyle) {
		headerCell = header.createCell(1);
		headerCell.setCellValue("Mes");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("Día");
		headerCell.setCellStyle(headerStyle);
		headerCell = header.createCell(7);
		headerCell.setCellValue("Año");
		headerCell.setCellStyle(headerStyle);
		headerCell = header.createCell(8);
		headerCell.setCellValue("Mes");
		headerCell.setCellStyle(headerStyle);
		headerCell = header.createCell(12);
		headerCell.setCellValue("IM");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(13);
		headerCell.setCellValue("IV");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(14);
		headerCell.setCellValue("SC");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(15);
		headerCell.setCellValue("OR");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(16);
		headerCell.setCellValue("IMA");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(17);
		headerCell.setCellValue("IU");
		headerCell.setCellStyle(headerStyle);
		headerCell = header.createCell(18);
		headerCell.setCellValue("Meses");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(19);
		headerCell.setCellValue("Días");
		headerCell.setCellStyle(headerStyle);
		headerCell = header.createCell(20);
		headerCell.setCellValue("Año");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(21);
		headerCell.setCellValue("Mes");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(22);
		headerCell.setCellValue("Día");
		headerCell.setCellStyle(headerStyle);
	}

	public String createTreatmentReport(Sheet sheet, Workbook workbook) {
		Row preHeader = sheet.createRow(0);
		Row header = sheet.createRow(1);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Año");
		headerCell.setCellStyle(headerStyle);

		Cell preHeaderCell = preHeader.createCell(0);
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 0, 2); // Fecha
		preHeaderCell.setCellValue("Fecha");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 3, 3); // Animal o lote
		preHeaderCell = preHeader.createCell(3);
		preHeaderCell.setCellValue("#Animal o lote");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 4, 4); // Tipo
		preHeaderCell = preHeader.createCell(4);
		preHeaderCell.setCellValue("Tipo");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 5, 5); // Tratamiento
		preHeaderCell = preHeader.createCell(5);
		preHeaderCell.setCellValue("Tratamiento");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 6, 6); // Producto
		preHeaderCell = preHeader.createCell(6);
		preHeaderCell.setCellValue("Producto");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 7, 8); // F. VTO
		preHeaderCell = preHeader.createCell(7);
		preHeaderCell.setCellValue("F. VTO");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 9, 9); // Lote
		preHeaderCell = preHeader.createCell(9);
		preHeaderCell.setCellValue("#Lote");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 10, 10); // Reg Ica
		preHeaderCell = preHeader.createCell(10);
		preHeaderCell.setCellValue("Registro ICA");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 11, 11); // Dosis
		preHeaderCell = preHeader.createCell(11);
		preHeaderCell.setCellValue("Dosis");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 12, 17); // Via de Vacunacion
		preHeaderCell = preHeader.createCell(12);
		preHeaderCell.setCellValue("Vía de vacunación");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 18, 19); // Tiempo retiro
		preHeaderCell = preHeader.createCell(18);
		preHeaderCell.setCellValue("Tiempo retiro");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 20, 22); // Fecha fin tratamiento
		preHeaderCell = preHeader.createCell(20);
		preHeaderCell.setCellValue("Fecha fin tratamiento");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 1, 23, 23); // Persona encargada
		preHeaderCell = preHeader.createCell(23);
		preHeaderCell.setCellValue("Persona encargada");
		preHeaderCell.setCellStyle(headerStyle);

		defineHeaders(headerCell, header, headerStyle);

		// Next, let's write the content of the table with a different style:

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		Row row = sheet.createRow(2);
		Cell cell = row.createCell(0);
		cell.setCellValue("John Smith");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(20);
		cell.setCellStyle(style);

		// Finally, let's write the content to a “temp.xlsx” file in the current
		// directory and close the workbook:

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateReport = formatter.format(date);
		System.out.println(dateReport);

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "Reporte_" + dateReport + ".xlsx";
		return fileLocation;
	}

	public String createMedicinetReport(Sheet sheet, Workbook workbook) {
		Row preHeader = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		headerStyle.setFont(font);

		Cell preHeaderCell = preHeader.createCell(0);
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell.setCellValue("Producto (Nombre Comercial)");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(1);
		preHeaderCell.setCellValue("Principio Activo");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(2);
		preHeaderCell.setCellValue("Presentación");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(3);
		preHeaderCell.setCellValue("Registro Ica No.");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(4);
		preHeaderCell.setCellValue("Año");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(5);
		preHeaderCell.setCellValue("Mes");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(6);
		preHeaderCell.setCellValue("Día");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(7);
		preHeaderCell.setCellValue("Entran (CantidadComprada)");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(8);
		preHeaderCell.setCellValue("Salen (Cantidad Usada)");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(9);
		preHeaderCell.setCellValue("Año");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(10);
		preHeaderCell.setCellValue("Mes");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(11);
		preHeaderCell.setCellValue("Día");
		preHeaderCell.setCellStyle(headerStyle);
		
		preHeaderCell = preHeader.createCell(12);
		preHeaderCell.setCellValue("N° lote");
		preHeaderCell.setCellStyle(headerStyle);
		
		preHeaderCell = preHeader.createCell(13);
		preHeaderCell.setCellValue("Saldo (Cantidad Restante)");
		preHeaderCell.setCellStyle(headerStyle);

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		Row row = sheet.createRow(2);
		Cell cell = row.createCell(0);
		cell.setCellValue("John Smith");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(20);
		cell.setCellStyle(style);

		// Finally, let's write the content to a “temp.xlsx” file in the current
		// directory and close the workbook:

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateReport = formatter.format(date);
		System.out.println(dateReport);

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "Reporte_Medicina_" + dateReport + ".xlsx";
		return fileLocation;
	}
	
	public String createFoodReport(Sheet sheet, Workbook workbook) {
		Row preHeader = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		headerStyle.setFont(font);

		Cell preHeaderCell = preHeader.createCell(0);
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell.setCellValue("Nombre");
		preHeaderCell.setCellStyle(headerStyle);

		setMerge(sheet, 0, 0, 1, 3); // Fecha
		preHeaderCell = preHeader.createCell(1);
		preHeaderCell.setCellValue("Año  Mes  Día");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(4);
		preHeaderCell.setCellValue("Entrada");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(5);
		preHeaderCell.setCellValue("Salida");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(6);
		preHeaderCell.setCellValue("Saldo");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(7);
		preHeaderCell.setCellValue("Fecha VTO");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(8);
		preHeaderCell.setCellValue("Datos compra");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(9);
		preHeaderCell.setCellValue("Registro ICA");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(10);
		preHeaderCell.setCellValue("N° lote");
		preHeaderCell.setCellStyle(headerStyle);

		preHeaderCell = preHeader.createCell(11);
		preHeaderCell.setCellValue("Observaciones");
		preHeaderCell.setCellStyle(headerStyle);

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		Row row = sheet.createRow(2);
		Cell cell = row.createCell(0);
		cell.setCellValue("John Smith");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(20);
		cell.setCellStyle(style);

		// Finally, let's write the content to a “temp.xlsx” file in the current
		// directory and close the workbook:

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateReport = formatter.format(date);
		System.out.println(dateReport);

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "Reporte_Alimento_" + dateReport + ".xlsx";
		return fileLocation;
	}
	protected void setMerge(Sheet sheet, int numRow, int untilRow, int numCol, int untilCol) {
		CellRangeAddress cellMerge = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
		sheet.addMergedRegion(cellMerge);

	}
}
