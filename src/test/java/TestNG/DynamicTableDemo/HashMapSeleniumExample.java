/**
 * 
 */
package TestNG.DynamicTableDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author DELL
 *
 */
public class HashMapSeleniumExample {
	
	public static HashMap<String, String> sharePriceExcelValuesFromXLS;
	public static HashMap<String, String> sharePriceExcelValuesFromWEB;
	
	public static void main(String args[]) throws BiffException, IOException
	{
		
		sharePriceExcelValuesFromXLS = readDatafromXLS("SharePriceList.xls");
		sharePriceExcelValuesFromWEB = readDatafromWEB();
		
		/*
		 * HashMap1: Storing the values from WebPage
		 * HashMap2: Storing the values from Excel
		 * Compare the values to validate data
		 * 
		 */
		
		String expected = sharePriceExcelValuesFromXLS.get("Optimus Finance");
		String actual = sharePriceExcelValuesFromWEB.get("Optimus Finance");
		
		if(actual.equalsIgnoreCase(expected))
		{
			System.out.println("TC Pass");
		} 
		else
		{
			System.out.println("TC Fails");
		}
		
		Set<String> keyList = sharePriceExcelValuesFromXLS.keySet();
		for(String key : keyList)
		{
			System.out.println(key);
			
			if((sharePriceExcelValuesFromXLS.get(key)).equalsIgnoreCase(sharePriceExcelValuesFromWEB.get(key)))
			{
				System.out.println("Pass for the " + key);
			}
			else
			{
				System.out.println("Fail for the " + key);
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static HashMap<String, String> readDatafromWEB()
	{
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://money.rediff.com/losers/bse/saily/groupa?src=gain_lose");
		
		List<WebElement> CurrentPriceList = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[4]"));
		List<WebElement> CompanyList = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[1]"));
		
		System.out.println("Current Price List is :" + CurrentPriceList.size());
		System.out.println("Company List is :" + CompanyList.size());
		
		HashMap<String, String> sharePriceExcelValuesFromWEB = new HashMap<String, String>();
		
		//for(int i=0;i<CurrentPriceList.size();i++)
		for(int i=0;i<7;i++)
		{
			
			System.out.println("Key ::" +CompanyList.get(i).getText().trim()  +"Value ::" +CurrentPriceList.get(i).getText().trim());
			
			sharePriceExcelValuesFromWEB.put(CompanyList.get(i).getText().trim(), CurrentPriceList.get(i).getText().trim());
			
		}
		
		return sharePriceExcelValuesFromWEB;
		
	}
	
	public static HashMap<String, String> readDatafromXLS(String fileName) throws BiffException, IOException
	{
		
		//Reading the data from XLS
		String directoryPath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(directoryPath+"/src/test/Resources/TestData/SharePriceList.xls");
		
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sh = wb.getSheet(0);
		int rows = sh.getRows();
		
		HashMap<String, String> sharePriceExcelValuesFromXLS = new HashMap<String, String>();
		
		for(int i=0;i<rows;i++)
		{
			
			Cell cl = sh.getCell(0, i);
			String key = cl.getContents();
			
			cl = sh.getCell(1, i);
			String value = cl.getContents();
			
			System.out.println("Key ::" +key);
			System.out.println("Value ::" +value);
			sharePriceExcelValuesFromXLS.put(key, value);
			System.out.println();
			
		}
		
		return sharePriceExcelValuesFromXLS;
		
	}
	

}
