package seleniumtests;

import utiliyclasses.CommonMethods;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalanderDemo extends CommonMethods{
	private static final String url = "https://www.aa.com/homePage.do?locale=en_US";
	private static final String expectedURLTitle = "American Airlines - Airline tickets and low fares at aa.com";
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		CommonMethods.openBrowserAndNavigateToWebsite("chrome", url);
		CommonMethods.checkIfOnWebsite(url, expectedURLTitle);
	}
	
	@Test
	private void testCalandarFunctionality() throws InterruptedException {
		WebElement departCalBtn = null;
		try {
			departCalBtn = webDriver.findElement(By.xpath("//button[@class='ui-datepicker-trigger'][1]"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		if(departCalBtn!=null) {
			departCalBtn.click();
		}else {Assert.fail(); }
		Thread.sleep(3000);
		
		String calMonth = "";
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
		WebElement firstCalMonth = null;
		WebElement nextMonthBtn = null;
		do {
			firstCalMonth = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month'])[1]")));
			nextMonthBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Next Month']")));
			
			calMonth = firstCalMonth.getText();
				if(!calMonth.contains("September"))
					nextMonthBtn.click();
		}while(!calMonth.contains("September")); 
		
		List<WebElement> calDays = webDriver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//a"));
		for(WebElement calDay: calDays) {
			if(calDay.getText().equals("11")) {
				calDay.click();
				break;
			}
		}	
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		CommonMethods.closeBrowser();
	}
}
