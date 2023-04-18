import java.io.FileWriter;
import java.util.ArrayList;

public class A 
{
	public static void main (String[] args) 
	{
		String fileName = "inPutFile.xlsx";
		fileName = "Catalogue Web Items 2023-04-12.xlsx";
		if (args.length > 0) {
			fileName = args[0];
		}
		
		int column = 5;
		if (args.length > 1) {
			column = Integer.parseInt(args[1]);
		}
		
		ExcelReader reader = new ExcelReader("input/" + fileName);
		ArrayList<Double> listOld = reader.ReadExcelFileColumn(column);
		ArrayList<Double> listNew = reader.ReadExcelFileColumn(column+1);
		
		// File textFile = new File("output.txt");
		try 
		{
			FileWriter writer = new FileWriter("output/output.txt");
			
			int count = 0;
			while (listOld.size() > count && listNew.size() > count) 
			{
				if (listOld.get(count) != -1.0 && listOld.get(count) != 0.0
					&& listNew.get(count) != -1.0 && listNew.get(count) != 0.0) 
				{
					writer.write("Update WebCatalogueItem Set HorizonBibNum = '" 
							+ listOld.get(count).intValue() + "' WHERE HorizonBibNum = '" 
							+ listNew.get(count).intValue() + "';\n");
				
					System.out.println("Update WebCatalogueItem Set HorizonBibNum = '" 
								+ listOld.get(count).intValue() + "' WHERE HorizonBibNum = '" 
								+ listNew.get(count).intValue() + "';");
				}
				
				count++;
			}
			writer.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
}
