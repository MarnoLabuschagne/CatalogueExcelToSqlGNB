import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader 
{
	public String ExcelFileName;
	
	public  ExcelReader (String excelFileName) 
	{
		ExcelFileName = excelFileName;
	}
	
	public ArrayList<Double> ReadExcelFileColumn (int col)
	{
		ArrayList<Double> list = new ArrayList<Double>();
		try 
		{
			File file = new File(ExcelFileName);
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			
			Row row = itr.next();
			while (itr.hasNext())
			{
				row = itr.next();
				
				try 
				{
					Double cell = row.getCell(col).getNumericCellValue();
					list.add(cell);
					
				} catch (Exception e)
				{
					//System.out.println(e.toString() + " ==half exit==");
					list.add(-1.0);
				}
			}
			wb.close();
		} catch (Exception e) 
		{
			System.out.println(e.toString() + " ==complete exit==");
		}
		
		return list;
	}
}
