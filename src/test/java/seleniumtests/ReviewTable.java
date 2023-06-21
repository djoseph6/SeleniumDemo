package seleniumtests;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class ReviewTable extends CommonMethods {
	private static String[] colLabels = {
			"Name","Product","#", "Date", "Street", "City", "State", "Zip", "Card", "Card Number", "Exp"
	};
	
   
	@BeforeClass
	private void prepareTest() {
        String url = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx";
        String browser = "chrome";
        openBrowserAndNavigateToWebsite(browser, url);
	}
	/* 	Function: Retrieve proper table, remove all client data where country is US.
	 * 
	 */
	@Test(priority = 1)
	private void testTable() {
        WebElement userName=webDriver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester", Keys.TAB);
        WebElement password=webDriver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("test",Keys.ENTER);
        
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='ctl00_MainContent_orderGrid']")));
        
       for(String label: colLabels) {
    		   Assert.assertTrue( table.getText().contains(label));
       }
       
       List<WebElement> tableRows = webDriver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
       for(WebElement row: tableRows) {
    	   System.out.println(row.getText());           
       }
       tableRows.removeIf((x)-> x.getText().contains("US"));
       
       for(WebElement row: tableRows) {
    	   System.out.println(row.getText());
    	   boolean rowDataContainsCountryUS = row.getText().contains("US");
    	   Assert.assertFalse(rowDataContainsCountryUS);
       }
    }
	/*
	 * Purpose: select checkbox where country is US and Payment method is Mastercard.
	 */
	@Test(priority = 2)
	private void checkBoxWhereStateIsUSAndCardIsMastercard() {
		List<WebElement> tableRows = webDriver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
		List<WebElement> checkBoxCol = webDriver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[1]/input"));
		
		for(WebElement row: tableRows) {
			String rowData = row.getText();
			if(rowData.contains("US") && rowData.contains("MasterCard")) {
				int validRowIndex = tableRows.indexOf(row) - 1;
				System.out.println(validRowIndex);
				WebElement checkBox = checkBoxCol.get(validRowIndex);
				if(!checkBox.isSelected()) {
					checkBox.click();
				}else {Assert.fail();}
			}else {continue;}
		}
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}
}
