package seleniumtests;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;
import utiliyclasses.DemoListener;

@Listeners(utiliyclasses.DemoListener.class)
public class AlertsDemo extends CommonMethods{
	private static final String url = "http://practice.syntaxtechs.net/javascript-alert-box-demo.php";
	private  static final String expectedURLTitle = "Syntax - Website to practice Syntax Automation Platform";
	
	@BeforeClass
	private  void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", url);
		checkIfOnWebsite(url,expectedURLTitle);
	}
	
	@Test
	private  void testAlertFunctionality() throws InterruptedException {
		List<Boolean> allTestResults = new ArrayList<Boolean>();
		
		
		WebElement clickMeButton1 = webDriver.findElement(By.xpath("//button[@class='btn btn-default']"));
		Boolean boolean1 = clickOnAlertButtonAndAccept(clickMeButton1);
		
		WebElement clickMeButton2 = webDriver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		Boolean boolean2 = clickOnAlertButtonAndAccept(clickMeButton2);
		Boolean boolean3 = clickOnAlertButtonAndDismiss(clickMeButton2);
		
		allTestResults.add(boolean1);
		allTestResults.add(boolean2);
		allTestResults.add(boolean3);
		
		allTestResults.forEach((x)->  System.out.println(x));
		allTestResults.forEach((x)->  Assert.assertTrue(x));
		
		//WebElement clickMeButton3 = webDriver.findElement(By.xpath("//button[normalize-space()='Click for Prompt Box']"));
		
	}
	
	@AfterClass(enabled=true)
	private  void closeTests() {
		closeBrowser();
	}
	
	private  boolean clickOnAlertButtonAndAccept(WebElement clickMeButton) throws InterruptedException {
		boolean testPassed = false;
		if(clickMeButton.isDisplayed()) {
			clickMeButton.click();
			Thread.sleep(3000);
			Alert confirmationAlert = null; 
			try {
				confirmationAlert = webDriver.switchTo().alert();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(confirmationAlert!=null) {
				confirmationAlert.accept();
				testPassed = true;
			}
		}
		return testPassed;
	}
	
	private  boolean clickOnAlertButtonAndDismiss(WebElement clickMeButton) throws InterruptedException {
		boolean testPassed = false;
		if(clickMeButton.isDisplayed()) {
			clickMeButton.click();
			Thread.sleep(3000);
//			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
//			wait.withMessage("Waiting 3 seconds for Alert to pop up");
			Alert confirmationAlert = null; 
			try {
				confirmationAlert = webDriver.switchTo().alert();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(confirmationAlert!=null) {
				confirmationAlert.dismiss();
				testPassed = true;
			}
		}
		return testPassed;
	}
	
}
