package at.scch.teclo.src.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import at.scch.teclo.src.BugzillaSetup;

public class LoginBasePage {
	
private final WebDriver driver;
	
	public LoginBasePage(WebDriver driver){
		this.driver = driver;
	}
	
	public static LoginBasePage navigateTo(WebDriver driver){
		driver.get(BugzillaSetup.getBaseURL() + "/");
		return PageFactory.initElements(driver, LoginBasePage.class);
	}
	
	public LoggedInBasePage logIn(String username, String password){

	    driver.findElement(By.id("login_link_top")).click();
	    driver.findElement(By.id("Bugzilla_login_top")).click();
	    driver.findElement(By.id("Bugzilla_login_top")).clear();
	    driver.findElement(By.id("Bugzilla_login_top")).sendKeys(username);    
	    
	    // sending Keys.TAB somehow triggers an change event for the Bugzilla_password_top element and makes it visible
	    driver.findElement(By.id("Bugzilla_login_top")).sendKeys(Keys.TAB);    
	    
//	    driver.findElement(By.id("Bugzilla_password_top")).click();
//	    driver.findElement(By.id("Bugzilla_password_top")).clear();
	    driver.findElement(By.id("Bugzilla_password_top")).sendKeys(password);
	    driver.findElement(By.id("log_in_top")).click();
	    
	    return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

}
