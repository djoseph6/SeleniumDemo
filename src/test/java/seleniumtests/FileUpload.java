package seleniumtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class FileUpload extends CommonMethods{
	private static final String url = "https://the-internet.herokuapp.com/";
	private static final String expectedURLTitle = "The Internet";
	private static final String filePath = "C:\\Users\\danie\\eclipse-workspace\\Selenium\\demofiles\\IMG_0597.JPG";
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", url);
		checkIfOnWebsite(url, expectedURLTitle);
	}
	
	@Test
	private void testUploadingFile() {
		WebElement fileUploadPageBtn = webDriver.findElement(By.linkText("File Upload"));
		fileUploadPageBtn.click();
		
		WebElement fileUploadBtn = webDriver.findElement(By.xpath("//input[@id='file-upload']"));
		fileUploadBtn.sendKeys(filePath);
		
		WebElement uploadFileBtn = webDriver.findElement(By.xpath("//input[@id='file-submit']"));
		uploadFileBtn.click();
		
		WebElement uploadVerifyMsg = webDriver.findElement(By.xpath("//h3"));
		System.out.println(uploadVerifyMsg.getText());
		Assert.assertEquals(uploadVerifyMsg.getText(), "File Uploaded!");
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		CommonMethods.closeBrowser();
	}

}
