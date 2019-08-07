package Testdataengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelsheet {
	XSSFWorkbook wb;
	XSSFSheet sh;
	public  Excelsheet (String excelpath)
	{
	File src = new File(excelpath);
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(src);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		wb = new XSSFWorkbook(fis);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
public String getData(int shnumber,int row,int column)
{
	sh = wb.getSheetAt(shnumber);
	String data = sh.getRow(row).getCell(column).getStringCellValue();

return data;
}
public int getrowgount(int sheetIndex)
{
	int row = wb.getSheetAt(sheetIndex).getLastRowNum();
	row = row+1;
	return row;
}
}
