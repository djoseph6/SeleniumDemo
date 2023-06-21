package seleniumtests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;
import org.openqa.selenium.*;

public class JSExecutionDemo extends CommonMethods{
	private static final String url = "https://www.amazon.com/";
	private static final String expectedURLTitle = "Amazon.com. Spend less. Smile more.";
	
	@BeforeClass
	private static void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", url);
		checkIfOnWebsite(url, expectedURLTitle);
	}
	
	@Test
	private void testJSExectionsOnAmazonWebsite() throws InterruptedException {
		JavascriptExecutor jsE = (JavascriptExecutor) webDriver;
		jsE.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(3000);
		jsE.executeScript("window.scrollBy(0, -500)");
		Thread.sleep(3000);
		
		WebElement allDropDownButton = webDriver.findElement(By.xpath("//div/select[@id='searchDropdownBox']"));
		allDropDownButton.click();
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		CommonMethods.closeBrowser();
	}

}
