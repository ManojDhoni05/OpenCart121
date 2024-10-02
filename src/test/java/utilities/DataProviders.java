package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws Exception
	{
		String path=".//testData//OpenCartLoginTestData.xlsx";
		
		Excelutility xlutil=new Excelutility(path);// passing the path through constructor to excel utility
		
		int totalRows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1); // sheet name and row number
		
		String loginData[][]=new String[totalRows][totalcols];
		
	    for(int i=1;i<=totalRows;i++) //i==1 because first row header  //for rows we should use <= because rowcount counts from 0 
		{
			for(int j=0;j<totalcols;j++)//0 
			{
				loginData[i-1][j]=xlutil.getCelldata("Sheet1", i, j);// 1,0  //i starting from 1 so we should use -1 one to store[0][0] 
			}
		}
		
		return loginData;
		
	}
	
	
	//Data provider 2
	
	//Data provider 3
}
