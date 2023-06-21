package utiliyclasses;

import org.openqa.selenium.*;


public class GoogleChromeWebProperties {
	
private WebDriver drive;
	
	
	public GoogleChromeWebProperties(){
		System.setProperty("selenium-chrome-driver-4.9.1", "C:\\selenium-java-4.9.1");
		
	}
	
	public WebDriver getWebDriver() {
		return drive;
		
	}
	

}
