package seleniumtests;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class SyntaxDropDownDemo extends CommonMethods{
	private static final String facebookURL = "https://www.facebook.com/";
	private static List<String> expectedMonthDropDownOptions;
	private static List<String> expectedDayDropDownOptions;
	private static List<String> expectedYearDropDownOptions;
//	private static final String[] expectedMonthDropDownOptions2 = {"Jan\r\n"
//			+ "Feb\r\n"
//			+ "Mar\r\n"
//			+ "Apr\r\n"
//			+ "May\r\n"
//			+ "Jun\r\n"
//			+ "Jul\r\n"
//			+ "Aug\r\n"
//			+ "Sep\r\n"
//			+ "Oct\r\n"
//			+ "Nov\r\n"
//			+ "Dec"};
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome",facebookURL );
		checkIfOnFacebook();
	}
	//This method can be refactored
	@Test
	private void checkDropDownList() throws InterruptedException {
		WebElement createNewAccount = null;
		try {
			 createNewAccount = webDriver.findElement(By.xpath("//div/a[@data-testid ='open-registration-form-button']"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
	//	Checking month dropdown
		if(createNewAccount!=null) {
			createNewAccount.click();
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			WebElement monthDropDown = null;
			try {
				 monthDropDown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='month']")));
			}catch(TimeoutException | NoSuchElementException e) {
				e.printStackTrace();
			}
			
			if(monthDropDown!=null) {
//				String monthOptions1 = monthDropDown.getText();  //gets all month options but hard to compare to expected values
//				System.out.println(monthOptions1);
//				
//				List<String> monthOptions2 = Arrays.asList(monthOptions1);
//				prepareMonthDropDownOptions();
//				Assert.assertEquals(monthOptions2, expectedMonthDropDownOptions2.toString());

				Select months = new Select(monthDropDown);
				prepareMonthDropDownOptions();
				List<WebElement> monthOptions = months.getOptions();
					for(WebElement mon: monthOptions) {
						String month = mon.getText();
						System.out.println(month);
						boolean containsMonth = expectedMonthDropDownOptions.contains(month);
						Assert.assertEquals(containsMonth, true);
					}
				months.selectByVisibleText("Mar");
				String displayedMonth = months.getFirstSelectedOption().getText(); //gets selected option
				System.out.println(displayedMonth);
				Assert.assertEquals(displayedMonth, "Mar");
				
			}else {Assert.fail(); }
			
		}else {Assert.fail(); }
		
		// Checking day dropdown
		if(createNewAccount!=null) {
//			createNewAccount.click();
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			WebElement dayDropDown = null;
			try {
				 dayDropDown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='day']")));
			}catch(TimeoutException | NoSuchElementException e) {
				e.printStackTrace();
			}
			
			if(dayDropDown!=null) {
//				String monthOptions1 = monthDropDown.getText();  //gets all month options but hard to compare to expected values
//				System.out.println(monthOptions1);
//				
//				List<String> monthOptions2 = Arrays.asList(monthOptions1);
//				prepareMonthDropDownOptions();
//				Assert.assertEquals(monthOptions2, expectedMonthDropDownOptions2.toString());

				Select days = new Select(dayDropDown);
				prepareDayDropDownOptions();
				List<WebElement> dayOptions = days.getOptions();
					for(WebElement day: dayOptions) {
						String d = day.getText();
						System.out.println(d);
						boolean containsDay = expectedDayDropDownOptions.contains(d);
						Assert.assertEquals(containsDay, true);
					}
				days.selectByVisibleText("21");
				String displayedDay = days.getFirstSelectedOption().getText(); //gets selected option
				System.out.println(displayedDay);
				Assert.assertEquals(displayedDay, "21");
				
			}else {Assert.fail(); }
			
		}else {Assert.fail(); }
		
		
		// Checking year dropdown
				if(createNewAccount!=null) {
//					createNewAccount.click();
					WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
					WebElement yearDropDown = null;
					try {
						 yearDropDown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='year']")));
					}catch(TimeoutException | NoSuchElementException e) {
						e.printStackTrace();
					}
//					try {
//						yearDropDown = webDriver.findElement(By.xpath("//select[@id='year']"));
//					}catch(NoSuchElementException e) {
//						e.printStackTrace();
//					}
					
					
					if(yearDropDown!=null) {
//						String monthOptions1 = monthDropDown.getText();  //gets all month options but hard to compare to expected values
//						System.out.println(monthOptions1);
//						
//						List<String> monthOptions2 = Arrays.asList(monthOptions1);
//						prepareMonthDropDownOptions();
//						Assert.assertEquals(monthOptions2, expectedMonthDropDownOptions2.toString());

						Select years = new Select(yearDropDown);
						prepareYearDropDownOptions();
						List<WebElement> yearOptions = years.getOptions();
							for(WebElement year: yearOptions) {
								String yr = year.getText();
								System.out.println(yr);
								boolean containsYear = expectedYearDropDownOptions.contains(yr);
								Assert.assertEquals(containsYear, true);
							}
						years.selectByVisibleText("2021");
						String displayedDay = years.getFirstSelectedOption().getText(); //gets selected option
						System.out.println(displayedDay);
						Assert.assertEquals(displayedDay, "2021");
						
					}else {Assert.fail(); }
					
				}else {Assert.fail(); }
		
	}
	
	
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}
	
	private static void prepareMonthDropDownOptions() {
		String[] monthArr = {"Jan","Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		expectedMonthDropDownOptions = Arrays.asList(monthArr);
	}
	
	private static void prepareDayDropDownOptions() {
		String[] dayArr = new String[31];
		for(int x=0;x<31;x++) {
			dayArr[x] = String.valueOf(x+1);
		}
		expectedDayDropDownOptions = Arrays.asList(dayArr);
	}
	
	private static void prepareYearDropDownOptions() {
		String[] yearArr = new String[119];
		for(int x=0, year=1905;x<119 || year<2024;x++, year++) {
				yearArr[x] = String.valueOf(year);	
		}
		expectedYearDropDownOptions = Arrays.asList(yearArr);
	}
	
	
}
