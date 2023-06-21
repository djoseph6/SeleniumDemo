package seleniumtests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class WaitsReview extends CommonMethods {
	
	@Test
    public void testWaits() {
        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        String browser = "chrome";
        openBrowserAndNavigateToWebsite(browser, url);

//        find the checkbox and click on it
        WebElement checkbox = webDriver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();

//        get the button and click on it
        WebElement removeButton = webDriver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();

//        we have to verify that the text it's gone appears up
        WebElement text = webDriver.findElement(By.xpath("//button[text()='Add']/following-sibling::p"));
        System.out.println(text.getText());


//        get the button enable
        WebElement enableBtn = webDriver.findElement(By.xpath("//form[@id='input-example']/child::button"));
        enableBtn.click();
//explicit wait
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
//        wait until
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='input-example']/child::input")));
//   now send some text
        WebElement textBox = webDriver.findElement(By.xpath("//form[@id='input-example']/child::input"));
        System.out.println(textBox.isEnabled());
        Assert.assertTrue(textBox.isEnabled());
    }
	
	@AfterClass
	private void endTests() {
		CommonMethods.closeBrowser();
	}
}
