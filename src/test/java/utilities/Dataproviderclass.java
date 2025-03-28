package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviderclass  {
	
	

	    @DataProvider(name = "LoginData")
	    public String[][] getData() throws IOException {
	        
	        // Path to the Excel file
	        String path = ".\\testData\\OpenCartLoginData.xlsx";

	        // Create an object of ExcelUtility class
	        ExcelUtilityClass xlutil = new ExcelUtilityClass(path);

	        // Get total rows and columns from the sheet
	        int totalRows = xlutil.getRowCount("Sheet1");
	        int totalCols = xlutil.getCellCount("Sheet1", 1);

	        // Create a 2D array to store data
	        String[][] loginData = new String[totalRows ][totalCols];

	        // Loop through the Excel sheet to fetch data
	        for (int i = 1; i < totalRows; i++) { // Start from 1 to skip the header row
	            for (int j = 0; j < totalCols; j++) {
	                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
	            }
	        }

	        return loginData; // Return the 2D array
	    }
	} 

