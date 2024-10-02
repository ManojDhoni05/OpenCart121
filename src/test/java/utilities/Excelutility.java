package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutility {
	//Only class no main method and create everything as , so can easily access without objects
	
	//This utils method is bit different from what we used earlier, here we have removed all the static keywords and for file path
	// we have created one constructor and we will create object of this class and invoke the constructor and pass file path
	
	//some changes made in set cell data method as well
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	public String path;
	
	public Excelutility(String path)
	{
		this.path=path;
	}
public int getRowCount(String sheet) throws Exception
{
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(sheet);
	int Rowcount= ws.getLastRowNum();	
	wb.close();
	fi.close();
	return Rowcount;
}
	
public  int getCellCount(String sheet,int Rownum) throws Exception
{
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(sheet);
	row=ws.getRow(Rownum);
	int Cellnum= row.getLastCellNum();	
	wb.close();
	fi.close();
	return Cellnum;
}

public  String getCelldata(String sheet,int Rownum,int columnnum) throws Exception
{
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(sheet);
	row=ws.getRow(Rownum);
	cell=row.getCell(columnnum);
	String data;
	
	//if the data is not available in a particular cell. then it will throw an exception so try catch needed
	//if it throws exception then catch will return "" empty string
	try
	{
		//data=cell.toString();// this also correct
	DataFormatter formatter=new DataFormatter(); //this also same as tostring()
	data=formatter.formatCellValue(cell);
	}
	catch(Exception e)
	{
		data="";
	}
	wb.close();
	fi.close();
	return data;
}

public  void setCelldata(String sheetName,int Rownum,int columnnum,String data) throws Exception
{
	
	//Reading and writing we will do both in same cell, because we will use the same row to write something
	File xlfile=new File(path);
	if(!xlfile.exists())// if file not exists then create a new file
	{
		wb=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		wb.write(fo);
		
	}
	
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	
	if(wb.getSheetIndex(sheetName)==-1)// if sheet not exists then create a new sheet
	{
		wb.createSheet(sheetName);
		
	}
	ws=wb.getSheet(sheetName);
	if(ws.getRow(Rownum)==null) //if row not exists create a new row
		ws.createRow(Rownum);
	

	row=ws.getRow(Rownum);
	cell=row.createCell(columnnum);
	cell.setCellValue(data);
	fo=new FileOutputStream(path);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
}


public  void fillGreenColor(String sheet,int Rownum,int columnnum) throws Exception
{
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(sheet);
	row=ws.getRow(Rownum);
	cell=row.getCell(columnnum);
	
	style=wb.createCellStyle();
	
	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	fo=new FileOutputStream(path);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	
}

public  void fillRedColor(String sheet,int Rownum,int columnnum) throws Exception
{
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet(sheet);
	row=ws.getRow(Rownum);
	cell=row.getCell(columnnum);
	
	style=wb.createCellStyle();
	
	style.setFillForegroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	fo=new FileOutputStream(path);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	
}






}
