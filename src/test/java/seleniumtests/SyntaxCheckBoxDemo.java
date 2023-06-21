package seleniumtests;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


import utiliyclasses.CommonMethods;

public class SyntaxCheckBoxDemo extends CommonMethods{
	
	private static final String synCheckBoxDemoURL = "http://practice.syntaxtechs.net/basic-checkbox-demo.php";
	private static final String expectedSuccessMesaage = "Success - Check box is checked";
	private static final String expectedMultipleUncheckedBoxLabel = "Check All";
	private static final String expectedMultipleCheckedBoxLabel = "Uncheck All";
	private static final String expectedSingleCheckBoxWaitSucessMessage = "Syntax Practice.";
	
	@BeforeClass
	private void prepareTest() throws InterruptedException {
		openBrowserAndNavigateToWebsite("chrome", synCheckBoxDemoURL);
		checkIfOnWebsite(synCheckBoxDemoURL, "Syntax - Website to practice Syntax Automation Platform");
	}
	
	@Test
	private void testSingleCheckBox() throws InterruptedException {
		WebElement singleCheckBox = null;
		
		try {
			singleCheckBox = webDriver.findElement(By.xpath("//label/input[@id = 'isAgeSelected']"));
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		
		
		if(singleCheckBox!=null) {
			boolean isSelected = singleCheckBox.isSelected();
			Assert.assertEquals(isSelected, false);
				if(isSelected==false) {
					singleCheckBox.click();
					Thread.sleep(2000);
					isSelected = singleCheckBox.isSelected();
					String successMessage = webDriver.findElement(By.xpath("//div/div[@id = 'txtAge']")).getText();
					System.out.println(successMessage);
					Assert.assertEquals(isSelected, true);
					Assert.assertEquals(successMessage,expectedSuccessMesaage);	
				}else {
					Assert.fail();
				}
				
		}else { Assert.fail(); }
		
		
	}
	
	@Test
	private void testMultipleCheckBoxes() throws InterruptedException {
		List<WebElement> checkBoxList = new ArrayList<WebElement>();
		WebElement check_UncheckAll = null;
		try {
			 checkBoxList = webDriver.findElements(By.xpath("//label/input[@class = 'cb1-element']"));
			 check_UncheckAll = webDriver.findElement(By.xpath("//input[@id='check1']"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		if(check_UncheckAll!=null) {
			String checkAllButton = check_UncheckAll.getAttribute("value").toString();
			System.out.println(checkAllButton);
			Assert.assertEquals(checkAllButton,expectedMultipleUncheckedBoxLabel);	
		}
		
		if(!checkBoxList.isEmpty()) {
			for(WebElement checkBox: checkBoxList) {
				checkBoxNoSelectedSoClick(checkBox);
			}
		}else { Assert.fail(); }
		
		if(check_UncheckAll!=null) {
			String uncheckAllButton = check_UncheckAll.getAttribute("value").toString();
			System.out.println(uncheckAllButton);
			Assert.assertEquals(uncheckAllButton,expectedMultipleCheckedBoxLabel);	
		}
		
	}
	
	@Test(priority=1)
	private void testWaitOfSingleCheckBox() throws InterruptedException {
		WebElement singleCheckBox = null;
		try {
			singleCheckBox = webDriver.findElement(By.xpath("//input[@id='myCheck']"));
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		if(singleCheckBox!=null) {
			boolean isSelected = singleCheckBox.isSelected();
				if(isSelected==false) {
					singleCheckBox.click();
					Thread.sleep(10000);
					
//					WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
//					String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='GFG_DOWN']"))).getText();

					
					String successMessage = null;
					try {
					 successMessage = webDriver.findElement(By.xpath("//p[@id='GFG_DOWN']")).getText();
					}catch(NoSuchElementException e) {
						e.printStackTrace();
					}
					System.out.println(successMessage);
					Assert.assertEquals(successMessage,  expectedSingleCheckBoxWaitSucessMessage);
					
				}else { Assert.fail(); }
		}else {Assert.fail(); }
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}
	
	private static void checkBoxNoSelectedSoClick(WebElement checkBox) throws InterruptedException {
		if(checkBox!=null) {
			boolean isSelected = checkBox.isSelected();
			Assert.assertEquals(isSelected, false);
				if(isSelected==false) {
					checkBox.click();
					Thread.sleep(2000);
					isSelected = checkBox.isSelected();

					Assert.assertEquals(isSelected, true);
				}else {
					Assert.fail();
				}
				
		}else { Assert.fail(); }
		
	}
}
