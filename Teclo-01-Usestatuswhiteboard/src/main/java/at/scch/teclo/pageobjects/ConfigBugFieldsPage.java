package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigBugFieldsPage {
	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	public ConfigBugFieldsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public ConfigBugFieldsPage setWhiteBoardStatusOn(){
		
		driver.findElement(By.id("usestatuswhiteboard-on")).click();
		
		driver.findElement(By.xpath("//form/input[5]")).click();
		
		return PageFactory.initElements(driver, ConfigBugFieldsPage.class);
	}
	
	public ConfigBugFieldsPage setWhiteBoardStatusOff(){
		driver.findElement(By.id("usestatuswhiteboard-off")).click();
		
		driver.findElement(By.xpath("//form/input[5]")).click();
		
		return PageFactory.initElements(driver, ConfigBugFieldsPage.class);
	}

}
