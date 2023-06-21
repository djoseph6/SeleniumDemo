package seleniumtests;



import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class VerifyURLAndTitle extends CommonMethods{
	private static final String facebookURL = "https://www.facebook.com/";
	private static final String googleURL = "https://www.google.com/";
	

	
	@BeforeClass
	private void verifyFacebookURLAndTitle() throws InterruptedException {
		//WebDriver driver = new GoogleChromeWebProperties().getWebDriver();
		openBrowserAndNavigateToWebsite("chrome", facebookURL);
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get(facebookURL);
		checkIfOnFacebook();
	}
	
	@Test
	private void verifyFacebookURLAndTitleAfterNavigationBackAndForward() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", googleURL);
		checkIfOnGoogle();
		
		webDriver.navigate().to(facebookURL);
		webDriver.navigate().refresh();
		Thread.sleep(1000);
		checkIfOnFacebook();
		
		webDriver.navigate().back();
		checkIfOnGoogle();
		
		webDriver.navigate().forward();
		checkIfOnFacebook();
		
	}
	
	@AfterClass(enabled=true)
	private void endTest() {
		closeBrowser();
	}
	
	
	

}
