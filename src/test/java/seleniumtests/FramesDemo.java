package seleniumtests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import utiliyclasses.CommonMethods;

public class FramesDemo extends CommonMethods{
	private static final String url = "https://chercher.tech/practice/frames";
	private static final String expectedURLTitle = "Frames Example for practice";
	private static  List<String> expectedAnimalDropDownOptions;
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", url);
		checkIfOnWebsite(url, expectedURLTitle);
	}
	
	@Test
	private void testSwitchingFrames() {
//		webDriver.switchTo().frame("frame2");
		WebElement frame2 = webDriver.findElement(By.xpath("//iframe[@id='frame2']"));
		switchFrames(frame2);
		WebElement dropDownLabel = webDriver.findElement(By.xpath("//b[text()='Animals :']"));
		
		if(dropDownLabel.isDisplayed()) {
			WebElement dropDownList = webDriver.findElement(By.xpath("//select[@id='animals']"));
			Select animalOptions = new Select(dropDownList);
			List<WebElement>options = animalOptions.getAllSelectedOptions();
			prepareDropDownList();
				for(WebElement option: options) {
					String optionText = option.getText();
					System.out.println(optionText);
					boolean optionsContainExpectedOptions = expectedAnimalDropDownOptions.contains(optionText);
					Assert.assertTrue(optionsContainExpectedOptions);
				}
//			Assert.assertEquals(options, Arrays.asList(expectedAnimalDropDownOptions));
			
			animalOptions.selectByVisibleText("Baby Cat");
			Assert.assertEquals(animalOptions.getFirstSelectedOption().getText(), "Baby Cat");
		}else {Assert.fail(); }
		
		webDriver.switchTo().defaultContent();
		
		WebElement frame1 = webDriver.findElement(By.xpath("//iframe[@id='frame1']"));
		switchFrames(frame1);
		WebElement topicInputBox = null;
		try {
			topicInputBox = webDriver.findElement(By.xpath("//body//input"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		if(topicInputBox!=null) {
			topicInputBox.clear();
			topicInputBox.sendKeys("I do not like baby cats");
		}
//		Assert.assertEquals(topicInputBox.getText().toString(), "I do not like baby cats");
		
		WebElement frame3InFrame1 = null;
		try {
			frame3InFrame1 = webDriver.findElement(By.xpath("//iframe[@id='frame3']"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		if(frame3InFrame1!=null) {
			switchFrames(frame3InFrame1);
			WebElement innerFrameCheckBox = webDriver.findElement(By.xpath("//input[@id='a']"));
			waitUntilWebElementIsVisible(innerFrameCheckBox);
			if(!innerFrameCheckBox.isSelected()) {
				innerFrameCheckBox.click();
				Assert.assertTrue(innerFrameCheckBox.isSelected());
			}else {Assert.fail();}	
		}else { Assert.fail(); }	
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}

	private static void switchFrames(WebElement frameWebElement) {
		if(frameWebElement!=null) {
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameWebElement));
		}
	}
	
	private static void waitUntilWebElementIsVisible(WebElement webE) {
		if(webE!=null) {
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(webE));
		}
	}
	
	private static void prepareDropDownList() {
		String[] arr = {	"Cat", "Baby Cat", "Big Baby Cat", "Avatar"	};
		expectedAnimalDropDownOptions = Arrays.asList(arr);
	}
}
