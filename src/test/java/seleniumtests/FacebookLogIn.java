package seleniumtests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utiliyclasses.CommonMethods;

public class FacebookLogIn extends CommonMethods{
	
	private static final String facebookURL = "https://www.facebook.com/";
	private static final String expectedInvalidLoginString1 = "Find your account and log in.";
	private static final String expectedInvalidLoginString2 = "Create a new Facebook account.";
	private static final String[][] invalidUserNamePasswordData = {
			
			{"fakeangie457", "fakePass"}, 
			{"fakejohn22", "fakePass"},
			{"fakeImnotreal@yahoo.com", "fakePass"}
		};
	private static int numOfTestsPassed;
	
	
	@AfterSuite
	public void shutDownDriver() {
		webDriver.quit();
	}
	@Test
	private void checkFacebookInvalidLogIn() throws InterruptedException {
		 
		openBrowserAndNavigateToWebsite("chrome", facebookURL);
		checkIfOnFacebook();
		
		for(int x=0; x<=invalidUserNamePasswordData.length-1;x++) {
			enterLogInDataAndVerifyInvalid(x);
		}
		System.out.println(numOfTestsPassed);
		Assert.assertEquals(numOfTestsPassed, invalidUserNamePasswordData.length);
	}
	
	@AfterClass(enabled=true)
	private void endTests() {
		closeBrowser();
	}
	
	private static void enterLogInDataAndVerifyInvalid(int logInAttempts) throws InterruptedException {
		if(logInAttempts<2) {
			WebElement usernameInputBox = webDriver.findElement(By.xpath("//input[@id='email']"));
			WebElement passwordInputBox = webDriver.findElement(By.xpath("//input[@id='pass']"));
			WebElement signInButton = webDriver.findElement(By.xpath("//button[@name='login']"));
	
	
				usernameInputBox.clear();
				usernameInputBox.sendKeys(invalidUserNamePasswordData[logInAttempts][0]);
				passwordInputBox.clear();
				passwordInputBox.sendKeys(invalidUserNamePasswordData[logInAttempts][1]);
				Thread.sleep(2000);
				signInButton.click();
				Thread.sleep(6000);
			
				String forgotPasswordMessage1 = "";
//				String forgotPasswordMessage2 = "";
			try {
				 forgotPasswordMessage1 = webDriver.findElement(By.cssSelector("a[href='https://facebook.com/login/identify/']")).getText();
//				 forgotPasswordMessage2 = webDriver.findElement(By.xpath("//a[normalize-space()='Create a new Facebook account.']")).getText();
			}catch(NoSuchElementException e) {
					e.getMessage();
				}
				
				if(forgotPasswordMessage1!=null && !forgotPasswordMessage1.isEmpty()) {
					System.out.println(forgotPasswordMessage1);
					if(forgotPasswordMessage1.equals(expectedInvalidLoginString1)) {
						numOfTestsPassed++;
						System.out.println(numOfTestsPassed);
					}
				}

//				webDriver.navigate().back();
				Thread.sleep(2000);
			}else {
				WebElement usernameInputBox = webDriver.findElement(By.xpath("//input[@id='email']"));
				WebElement passwordInputBox = webDriver.findElement(By.xpath("//input[@id='pass']"));
				WebElement signInButton = webDriver.findElement(By.xpath("//button[@name='login']"));
		
		
					usernameInputBox.clear();
					usernameInputBox.sendKeys(invalidUserNamePasswordData[logInAttempts][0]);
					passwordInputBox.clear();
					passwordInputBox.sendKeys(invalidUserNamePasswordData[logInAttempts][1]);
					Thread.sleep(2000);
					signInButton.click();
					Thread.sleep(6000);
				
//					String forgotPasswordMessage1 = "";
					String forgotPasswordMessage2 = "";
				try {
//					 forgotPasswordMessage1 = webDriver.findElement(By.cssSelector("a[href='https://facebook.com/login/identify/']")).getText();
					 forgotPasswordMessage2 = webDriver.findElement(By.xpath("//a[normalize-space()='Create a new Facebook account.']")).getText();
				}catch(NoSuchElementException e) {
						e.printStackTrace();
					}
				
					 if(forgotPasswordMessage2!=null && !forgotPasswordMessage2.isEmpty()) {
						System.out.println(forgotPasswordMessage2);
						 if(forgotPasswordMessage2.equals(expectedInvalidLoginString2)){
							numOfTestsPassed++;
							System.out.println(numOfTestsPassed);
						}
					}
//					webDriver.navigate().back();
					Thread.sleep(2000);
			}
	}

}
