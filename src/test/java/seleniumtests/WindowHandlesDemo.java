package seleniumtests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class WindowHandlesDemo extends CommonMethods{
	private static final String url = "http://accounts.google.com/signup";
	private static final String expectedURLTitle = "Sign in - Google Accounts";
	private static final String expectedHelpWindowHandleTitle = "Google Account Help";
	private static final String expectedPrivacyWindowHandle = "Privacy Policy – Privacy & Terms – Google";
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", url);
		checkIfOnWebsite(expectedURLTitle);
	}
	
	@Test
	private void changeWindowHandles() throws Exception{
		WebElement helpLink = null;
		WebElement privacyLink = null;
		try {
			helpLink = webDriver.findElement(By.linkText("Help"));
			privacyLink = webDriver.findElement(By.xpath("//a[text()='Privacy']"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		if(helpLink!=null || privacyLink!=null) {
			helpLink.click();
			privacyLink.click();
		}else {Assert.fail();}
		
		Set<String>windowHandles = webDriver.getWindowHandles();
		int numberOfHandlesVerified = 0;
		for(String windowHandle:windowHandles) {
			webDriver.switchTo().window(windowHandle);
			Thread.sleep(3000);
			String windowHandleTitle = webDriver.getTitle();
			System.out.println(windowHandleTitle);
			
			switch(windowHandleTitle) {
			case expectedURLTitle: numberOfHandlesVerified++; break;
			case expectedHelpWindowHandleTitle: numberOfHandlesVerified++; break;
			case expectedPrivacyWindowHandle: numberOfHandlesVerified++; break;
			default: Assert.fail("Window handle title not expected");
			}
		}
	    Assert.assertEquals(numberOfHandlesVerified, 3);
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}

}
