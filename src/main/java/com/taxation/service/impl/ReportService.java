package com.taxation.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.taxation.global.exception.FileStorageException;
import com.taxation.global.exception.MyFileNotFoundException;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;

@Service
public class ReportService {
	
	public	Map<String, Object[]> createAllPaidAndDueInfo(List<TaxDetail>	taxDetails,String	all){
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "Samagra Id", "Unique Id", "Subholder Name","Resident Name","Amount "+all,"Date" });
		int	counter=2;
		for(int	i=0;i<taxDetails.size();i++){
			TaxDetail	td	=	taxDetails.get(i);
			Property	p=td.getProperty();
			empinfo.put(String.valueOf(counter), new Object[] { p.getSamagraId(), p.getCustomUniqueId(), p.getSubHolder(),p.getResidentName(),td.getAmount(),String.valueOf(td.getLastTaxPaidOn()==null?"":td.getLastTaxPaidOn()) });
			counter++;
		}
		
		return	empinfo;
	}

	public	Map<String, Object[]> createPropertyInfo(List<Property>	properties){
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "Number","Area","Village","District","Tehsil","Pincode", "Unique Id","Samagra Id", "Subholder Name","Resident Name","Water Connected","Transfered to" });
		int	counter=2;
		for(int	i=0;i<properties.size();i++){
			Property	p	=	properties.get(i);
			
			empinfo.put(String.valueOf(counter), new Object[] { p.getPropertyNumber(),p.getArea(),p.getVillage(),p.getDistrict(),p.getTehsil(),p.getPincode(),p.getCustomUniqueId(),p.getSamagraId(),p.getSubHolder(),p.getResidentName(),p.getWaterConnected()?"YES":"NO",p.getTransferredToSamagraId()==null?"":p.getTransferredToSamagraId()});
			counter++;
		}
		
		return	empinfo;
	}
	
	public Resource getReport(Map<String, Object[]> empinfo) throws Exception {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Tax Info");

		// Create row object
		XSSFRow row;

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;

		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = workbook.createFont();
		font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font); 
		
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				XSSFCell cell = row.createCell(cellid++);
				if(obj instanceof Float){
					cell.setCellValue((Float)obj);
				}else
				cell.setCellValue((String) obj);
				
				if(rowid==1)cell.setCellStyle(style);
				
				spreadsheet.autoSizeColumn(cell.getColumnIndex());
			}
		}
		
		if(keyid.size()>1){
		
		XSSFRow rowTotal = spreadsheet.createRow(rowid + 2);

		XSSFCellStyle styletotal=workbook.createCellStyle();
		styletotal.setFont(font);
		styletotal.setBorderTop(BorderStyle.THIN);
		
		XSSFCell cellTotalText = rowTotal.createCell(3);
        cellTotalText.setCellValue("Total:");
        cellTotalText.setCellStyle(styletotal);

        XSSFCell cellTotal = rowTotal.createCell(4);
        cellTotal.setCellType(CellType.FORMULA);
        cellTotal.setCellFormula("SUM(E2:E"+rowid+")");
        cellTotal.setCellStyle(styletotal);

		}
   

		String fileName = "TaxReport"+UUID.randomUUID()+".xlsx";
		Path p = Paths.get("./tempUploads").toAbsolutePath().normalize();
		try {
			Files.createDirectories(p);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		FileUtils.cleanDirectory(new File("./tempUploads"));
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("./tempUploads/" + fileName));
		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");
		return loadFileAsResource(fileName, p);
	}

	public Resource getPropertyReport(Map<String, Object[]> empinfo) throws Exception {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Property Info");

		// Create row object
		XSSFRow row;

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;

		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = workbook.createFont();
		font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font); 

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				XSSFCell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
				if(rowid==1)cell.setCellStyle(style);
				spreadsheet.autoSizeColumn(cell.getColumnIndex());
			}
		}
		
		String fileName ="PropertyReport"+UUID.randomUUID()+".xlsx";
		Path p = Paths.get("./tempUploads").toAbsolutePath().normalize();
		try {
			Files.createDirectories(p);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		FileUtils.cleanDirectory(new File("./tempUploads"));
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("./tempUploads/" + fileName));
		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");
		return loadFileAsResource(fileName, p);
	}
	
	public Resource loadFileAsResource(String fileName, Path p) {
		try {
			Path filePath = p.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}
