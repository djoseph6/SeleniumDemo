package utiliyclasses;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class CommonMethods {
	
	public static WebDriver webDriver;
	private static final String expectedFBTitle = "Facebook - log in or sign up";
	private static final String expectedFBURL = "https://www.facebook.com/";
	private static final String expectedGGLTitle = "Google";
	private static final String expectedGGLURL = "https://www.google.com/";
	
	
	public static void openBrowserAndNavigateToWebsite(String driver, String url){
		System.setProperty("selenium-chrome-driver-4.9.1", "C:\\selenium-java-4.9.1");
		System.setProperty("selenium-firefox-driver-4.9.1", "C:\\selenium-java-4.9.1");
		switch(driver) {
		case "chrome": webDriver = new ChromeDriver(); break;
		case "firefox": webDriver = new FirefoxDriver(); break;
		}
		
		//implicitly wait 15 secs to retrieve any webelement
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//maximize window
		webDriver.manage().window().maximize();
		//navigate to the url
		webDriver.navigate().to(url);
	}
	
	public static void closeBrowser() {
		if(webDriver!= null) {
		webDriver.close();
		}
	}
	
	public static void sentText(String text, WebElement element) {
		element.clear();
		element.sendKeys(text);
	}
	
	public static void checkIfOnGoogle() throws InterruptedException {
		Thread.sleep(2000);
		String currentURL = webDriver.getCurrentUrl();
		System.out.println(currentURL);
		Assert.assertEquals(currentURL, expectedGGLURL);
		String currentTitle = webDriver.getTitle();
		System.out.println(currentTitle);
		Assert.assertEquals(currentTitle, expectedGGLTitle);
	}
	
	public static void checkIfOnFacebook() throws InterruptedException {
		Thread.sleep(2000);
		String currentURL = webDriver.getCurrentUrl();
		System.out.println(currentURL);
		Assert.assertEquals(currentURL, expectedFBURL);
		String currentTitle = webDriver.getTitle();
		System.out.println(currentTitle);
		Assert.assertEquals(currentTitle, expectedFBTitle);
	}
	
	public static void checkIfOnWebsite(String websiteURL,String expectedWebsiteTitle) throws InterruptedException {
		Thread.sleep(2000);
		String currentURL = webDriver.getCurrentUrl();
		System.out.println(currentURL);
		Assert.assertEquals(currentURL, websiteURL);
		String currentTitle = webDriver.getTitle();
		System.out.println(currentTitle);
		Assert.assertEquals(currentTitle, expectedWebsiteTitle);
	}
	
	public static void checkIfOnWebsite(String expectedWebsiteTitle) throws InterruptedException {
		Thread.sleep(2000);
		String currentTitle = webDriver.getTitle();
		System.out.println(currentTitle);
		Assert.assertEquals(currentTitle, expectedWebsiteTitle);
	}
	
	 public static void takeScreenshot(String fileName) throws IOException {
         TakesScreenshot ts = (TakesScreenshot) webDriver;
         File screenShot = ts.getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(screenShot, new File("C:\\Users\\danie\\eclipse-workspace\\Selenium\\screenshots\\" + fileName+".png"));
     }

}
