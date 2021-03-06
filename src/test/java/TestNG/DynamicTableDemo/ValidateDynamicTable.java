/**
 * 
 */
package TestNG.DynamicTableDemo;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author DELLs
 *
 */
public class ValidateDynamicTable {

	@SuppressWarnings("deprecation")
	
	//@Test
	public void noOfRowColDynamicTable() 
	{
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/web-table-element.php");
		
		/*
		 * WebElement Mytable =
		 * driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table"));
		 * 
		 * @SuppressWarnings("unchecked") List<WebElement> TableRows =
		 * (List<WebElement>)
		 * Mytable.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr/td[1]")
		 * );
		 * 
		 * for(int i=0;i<TableRows.size();i++) {
		 * 
		 * WebElement row = TableRows.get(i);
		 * 
		 * String rowText = row.getText();
		 * 
		 * System.out.println(rowText + "The table company headers");
		 * 
		 * //assert.assertEquals(0, 0) //assertEquals("Cox & Kings L", row.getText());
		 * 
		 * 
		 * if(rowText.equalsIgnoreCase("Cox & Kings L")) {
		 * System.out.println("The correct text matched in table row number:"); }
		 * 
		 * 
		 * }
		 */
		
		
		  //Table lenght 
		WebElement Table = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table"));
		System.out.println("Table size is : " +Table.getSize());
		 
		 //No.of Columns
        List <WebElement> col = driver.findElements(By.xpath(".//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
        System.out.println("No of cols are : " +col.size()); 
        
        //No.of rows 
        List <WebElement> rows = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]")); 
        System.out.println("No of rows are : " + rows.size());

	}
	
	@SuppressWarnings("deprecation")
	//@Test
	public void partiCularRowColDynamicTable() 
	{
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/web-table-element.php");
	
		WebElement baseTable = driver.findElement(By.tagName("table"));
		
		//To find third row of table
		WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
        String rowtext = tableRow.getText();
		System.out.println("Third row of table : "+rowtext);
		    
		//to get 3rd row's 2nd column data
		WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
		String valueIneed = cellIneed.getText();
		System.out.println("Cell value is : " + valueIneed);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getMaxValueInColDynamicTable() throws ParseException 
	{
	
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/web-table-element.php");
		
		 String max;
	     double m=0,r=0;
		 
	       //No. of Columns
	        List<WebElement>  col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
	        System.out.println("Total No of columns are : " +col.size());
	        
	        //No.of rows
	        List<WebElement>  rows = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	        System.out.println("Total No of rows are : " + rows.size());
	        
	        //Maximum of all the Values in a Column (Max of current price column[4])
	        for (int i =1;i<rows.size();i++)
	        {    
	      
	        	max= driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
	            
	            NumberFormat f = NumberFormat.getNumberInstance(); 
	            Number num = f.parse(max);
	            max = num.toString();
	            m = Double.parseDouble(max);
	            if(m>r)
	             {    
	                r=m;
	             }
	        }
	        
	        System.out.println("Maximum current price is : "+ r);
	
	}
	
	@SuppressWarnings("deprecation")
	//@Test
	public void getAllValuesDynamicTable()
	{
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/web-table-element.php");
		
		//To locate table.  	
    	WebElement mytable = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody"));
    	System.out.println("Table size is : " + mytable.getSize());
    	
    	//To locate rows of table. 
    	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
    	
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
    	
    	System.out.println("No of rows are : " + rows_table.size());
    	
    	//Loop will execute till the last row of table.
    	for (int row = 0; row < rows_count; row++) 
    	{
    		
    	//To locate columns(cells) of that specific row.
    	List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	
    	//To calculate no of columns (cells). In that specific row.
    	int columns_count = Columns_row.size();	
    	
    	System.out.println("No of cols are : " + Columns_row.size());
    	 
    	System.out.println("Number of cells In Row " + row + " are " + columns_count);
    	
    	//Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) 
    	    {
    	        // To retrieve text from that specific cell.
    	        String celtext = Columns_row.get(column).getText();
    	        
    	        //Assert.assertEquals("Varun Beverages Ltd.", Columns_row.get(column).getText());
    	        
    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
    	    }
    	    
    	  System.out.println("-------------------------------------------------- ");
       }
   	}
		
	
	@SuppressWarnings("deprecation")
	@Test
	public void getExactCompanyNameFromDynamicTable() throws InterruptedException
	{
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://money.rediff.com/losers/bse/saily/groupa?src=gain_lose");
		
		 //WebElement CompanyName;
		 String company = "Fortis Healthcare";
		 
	       //No. of Columns
	        List<WebElement>  col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
	        System.out.println("Total No of columns are : " +col.size());
	        
	        //No.of rows
	        List<WebElement>  rows = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	        System.out.println("Total No of rows are : " + rows.size());
	        
	        //Maximum of all the Values in a Column (Max of current price column[4])
	        for (int i =1;i<rows.size();i++)
	        {    
	      
	        	//driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (i+1)+ "]/td[1]")).getText();
	        	
	       String CompanyName= driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (i) + "]/td[1]/a")).getText();
	        
	                  
	            if(company.equalsIgnoreCase(CompanyName))
	             {   
	            	Thread.sleep(5000);
	            	driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (i) + "]/td[1]/a")).click();
	            	Reporter.log("Company Selected or Identified");
	            	System.out.println("The mapped Company Name from list is: " + CompanyName + " for " + company);
	            	System.out.println("The Company Name from list is: " + CompanyName);
	            	//CompanyName.click();
	            	break;
	            	                       		            	
	             }
	            
	        }
	}
	
}


