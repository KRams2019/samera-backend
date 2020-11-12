package com.mindtree.encryption.util;

import java.io.File;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile 
{

	public static Object writeFile() 
	{
		int i = -1;
		int userId = 1;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = xssfWorkbook.createSheet("TimeSheet");
		XSSFRow xssfRow = xssfSheet.createRow(++i);
		Cell cell = xssfRow.createCell(0);
		cell.setCellValue("Name");
		Cell cell2 = xssfRow.createCell(1);
		cell2.setCellValue("StartDate");
		
		do
		{
			LocalDate today = LocalDate.now();
			 LocalDate monday = today;
			 for(int k = 1;k<=7;k++ )
			 {
				 if(k==1)
				 {
					 while (monday.getDayOfWeek() != DayOfWeek.MONDAY)
					 {
					    monday = monday.minusDays(1);
					 }
				 }
				 else
				 {
					 monday = monday.plusDays(7);
				 }
				 
				  xssfRow = xssfSheet.createRow(++i);
					 cell = xssfRow.createCell(0);
					cell.setCellValue(userId);
					 cell2 = xssfRow.createCell(1);
					 System.out.println(userId+"----"+monday);
					 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					cell2.setCellValue(monday.format(myFormatObj));
			 }
			
		}
		while(++userId <=10);
		
		 try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File("employee.xlsx"));
	            xssfWorkbook.write(out);
	            out.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		return "file written successfully";
	}
	
}
